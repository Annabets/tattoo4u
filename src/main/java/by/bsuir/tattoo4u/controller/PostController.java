package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.PostRequestDto;
import by.bsuir.tattoo4u.dto.response.PostResponseDto;
import by.bsuir.tattoo4u.entity.*;
import by.bsuir.tattoo4u.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "api")
public class PostController {
    private final PostService postService;
    private final TokenService tokenService;
    private final UserService userService;
    private final PhotoService photoService;

    @Autowired
    public PostController(
            PostService postService,
            TokenService tokenService,
            UserService userService,
            PhotoService photoService
    ) {
        this.postService = postService;
        this.tokenService = tokenService;
        this.userService = userService;
        this.photoService = photoService;
    }

    @PostMapping(value = "add-post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> addPost(
            @RequestHeader("Authorization") String token,
            @ModelAttribute PostRequestDto postRequestDto
    ) {
        //token validation
        token = token.substring(7); //move to service

        String username = tokenService.getUsername(token);

        User user = userService.getByUsername(username);

        PhotoUpload photoUpload = new PhotoUpload(postRequestDto.getFile());

        try {
            Photo photo = photoService.save(photoUpload);

            Post post = new Post(postRequestDto.getDescription(), user, photo, postRequestDto.getTags());
            postService.save(post);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(value = "posts")
    public ResponseEntity<?> posts() {
        try {
            Iterable<Post> posts = postService.takePosts();

            Iterable<PostResponseDto> postDtoList = toDto(posts);

            return new ResponseEntity<>(postDtoList, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(value = "take-posts/{id}")
    public ResponseEntity<?> takeUserPosts(@PathVariable("id") User user) {
        try {
            if (user != null) {
                Iterable<Post> posts = postService.takePosts(user);

                Iterable<PostResponseDto> postDtoList = toDto(posts);

                return new ResponseEntity<>(postDtoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User with the specified id does not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(value = "take-posts")
    public ResponseEntity<?> takePosts(@RequestParam String tags) {
        try {
            Iterable<Post> posts = postService.takePosts(tags);

            Iterable<PostResponseDto> postDtoList = toDto(posts);

            return new ResponseEntity<>(postDtoList, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping(value = "delete-post/{post}")
    public ResponseEntity<?> deletePost(@PathVariable Post post, @RequestHeader("Authorization") String token) {
        try {
            //token validation
            token = token.substring(7); //move to service

            String username = tokenService.getUsername(token);

            User user = userService.getByUsername(username);

            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());

            if (post != null) {
                if (post.getAuthor().getId().equals(user.getId()) || roles.contains(RoleType.ADMIN.toString())) {
                    postService.delete(post);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Access is denied", HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>("Post with the specified id does not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    private Iterable<PostResponseDto> toDto(Iterable<Post> posts) {
        List<PostResponseDto> postDtoList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postDto = new PostResponseDto();
            postDto.fromPost(post);
            postDtoList.add(postDto);
        }

        return postDtoList;
    }
}
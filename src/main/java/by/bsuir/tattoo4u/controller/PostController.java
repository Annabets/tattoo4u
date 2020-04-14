package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.PostRequestDto;
import by.bsuir.tattoo4u.dto.response.PostResponseDto;
import by.bsuir.tattoo4u.entity.PhotoUpload;
import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "api")
public class PostController {
    private final PostService postService;
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, TokenService tokenService, UserService userService) {
        this.postService = postService;
        this.tokenService = tokenService;
        this.userService = userService;
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

        Post post = new Post(postRequestDto.getDescription(), user, postRequestDto.getTags());

        PhotoUpload photoUpload = new PhotoUpload(postRequestDto.getFile());

        try {
            postService.savePhoto(post, photoUpload);
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
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> deletePost(@PathVariable Post post) {
        try {
            if (post != null) {
                postService.delete(post);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
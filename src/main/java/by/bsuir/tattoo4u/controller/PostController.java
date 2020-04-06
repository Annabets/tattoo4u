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
    @PreAuthorize("hasAuthority('USER')")
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

            List<PostResponseDto> postDtoList = new ArrayList<>();

            for (Post post : posts) {
                PostResponseDto postDto = new PostResponseDto();
                postDto.fromPost(post);
                postDtoList.add(postDto);
            }

            return new ResponseEntity<>(postDtoList, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

//    @GetMapping(value = "take-posts")
//    public ResponseEntity<?> takePosts(@ModelAttribute List<String> tags){
//        try{
//            postService.takePosts(tags);
//        } catch (ServiceException e) {
//
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
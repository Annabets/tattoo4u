package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
    private final PostService postService;
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
        httpHeaders.set("Access-Control-Allow-Origin", "*");
    }

    @PostMapping("/add-post")
    public ResponseEntity<?> addPost(@RequestParam(name = "file") MultipartFile file) {
        Post post = new Post();
        try {
            postService.saveFile(post, file);
            postService.save(post);
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        } catch (ServiceException e) {
            ///
            return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/photos")
    public ResponseEntity<?> photos() {
        Iterable<Post> posts = postService.takePosts();
        return new ResponseEntity<>(posts, httpHeaders, HttpStatus.OK);
    }
}
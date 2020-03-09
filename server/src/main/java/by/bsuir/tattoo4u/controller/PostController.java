package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/new-post")
    public String addPost(@RequestParam("file") MultipartFile file) {
        Post post = new Post();

        try {
            postService.saveFile(post, file);
            postService.save(post);
            return "redirect:/gallery";
        } catch (ServiceException e) {
            ///
            return "error";
        }
    }

    @GetMapping("/gallery")
    public String openGallery(Model model) {
        Iterable<Post> posts = postService.takePosts();
        model.addAttribute("posts", posts);

        return "gallery";
    }

    @GetMapping("/add-post")
    public String addPostPage() {
        return "addPost";
    }
}

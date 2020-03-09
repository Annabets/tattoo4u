package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PostService {

    @Value("${upload.path}")
    private String uploadPath;

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void saveFile(Post post, MultipartFile file) throws ServiceException {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();

            if (originalFilename != null && !originalFilename.isEmpty()) {

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuid = UUID.randomUUID().toString();
                String resultFilename = uuid + "." + originalFilename;

                try {
                    file.transferTo(new File(uploadPath + "/" + resultFilename));
                    post.setFilename(resultFilename);
                } catch (IOException e) {
                    throw new ServiceException(e);
                }
            }
        }
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Iterable<Post> takePosts() {
        return postRepository.findAll();
    }
}

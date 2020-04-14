package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Photo;
import by.bsuir.tattoo4u.entity.PhotoUpload;
import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.PhotoRepository;
import by.bsuir.tattoo4u.repository.PostRepository;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Value("${cloudinary.cloudName}")
    private String cloudName;

    @Value("${cloudinary.apiSecret}")
    private String apiSecret;

    @Value("${cloudinary.apiKey}")
    private String apiKey;

    private PostRepository postRepository;
    private PhotoRepository photoRepository;
    private Cloudinary cloudinary = new Cloudinary();

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PhotoRepository photoRepository) {
        this.postRepository = postRepository;
        this.photoRepository = photoRepository;
    }

    public void savePhoto(Post post, PhotoUpload photoUpload) throws ServiceException {
        cloudinary.config.cloudName = cloudName;
        cloudinary.config.apiSecret = apiSecret;
        cloudinary.config.apiKey = apiKey;

        try {
            Map uploadResult = cloudinary.uploader().upload(
                    photoUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "image")
            );

            photoUpload.setPublicId((String) uploadResult.get("public_id"));

            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                photoUpload.setVersion(new Long((Integer) version));
            } else {
                photoUpload.setVersion((Long) version);
            }

            photoUpload.setSignature((String) uploadResult.get("signature"));
            photoUpload.setFormat((String) uploadResult.get("format"));
            photoUpload.setResourceType((String) uploadResult.get("resource_type"));

            Photo photo = new Photo();
            photo.setUrl(photoUpload.getUrl(cloudinary));

            photoRepository.save(photo);

            post.setPhoto(photo);
        } catch (IOException | RuntimeException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Iterable<Post> takePosts() {
        return postRepository.findAll();
    }

    @Override
    public Iterable<Post> takePosts(User user) throws ServiceException {
        return postRepository.findByAuthor(user);
    }

    @Override
    public Iterable<Post> takePosts(String tags) throws ServiceException {
        List<String> tagsList = parseTags(tags);
        return postRepository.findByTags(tagsList);
    }

    @Override
    public void delete(Post post) throws ServiceException {
        postRepository.delete(post);
    }

    private List<String> parseTags(String tags) {
        return Arrays.asList(tags.split("#"));
    }
}
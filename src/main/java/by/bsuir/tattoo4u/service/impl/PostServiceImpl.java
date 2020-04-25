package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.PostRepository;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
    public Iterable<Post> takeTr() throws ServiceException {
        return postRepository.findTr();
    }

    @Override
    public void delete(Post post) throws ServiceException {
        postRepository.delete(post);
    }

    @Override
    public void like(Post post, User user) throws ServiceException {
        Set<User> likes = post.getLikes();

        if (likes.contains(user)) {
            likes.remove(user);
        } else {
            likes.add(user);
        }

        save(post);
    }

    private List<String> parseTags(String tags) {
        return Arrays.asList(tags.split("#"));
    }
}
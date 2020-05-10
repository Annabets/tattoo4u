package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.PostRepository;
import by.bsuir.tattoo4u.service.PostService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Post> takePosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> takePosts(User user, Pageable pageable) throws ServiceException {
        return postRepository.findByAuthor(user, pageable);
    }

    @Override
    public Page<Post> takePosts(String tags, Pageable pageable) throws ServiceException {
        List<String> tagsList = parseTags(tags);
        return postRepository.findByTags(tagsList, pageable);
    }

    @Override
    public Page<Post> takeTrends(Pageable pageable) throws ServiceException {
        return postRepository.findTrends(pageable);
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
        return Arrays.asList(tags.split(" "));
    }
}
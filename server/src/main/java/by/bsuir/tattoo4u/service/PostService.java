package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void save(Post post) throws ServiceException;

    Page<Post> takePosts(Pageable pageable) throws ServiceException;

    Page<Post> takePosts(User user, Pageable pageable) throws ServiceException;

    Page<Post> takePosts(String tags, Pageable pageable) throws ServiceException;

    Page<Post> takeTrends(Pageable pageable) throws ServiceException;

    void delete(Post post) throws ServiceException;

    void like(Post post, User user) throws ServiceException;
}

package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.PhotoUpload;
import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;

import java.util.List;

public interface PostService {

    void save(Post post) throws ServiceException;

    Iterable<Post> takePosts() throws ServiceException;

    Iterable<Post> takePosts(User user) throws ServiceException;

    Iterable<Post> takePosts(String tags) throws ServiceException;

    void delete(Post post) throws ServiceException;
}

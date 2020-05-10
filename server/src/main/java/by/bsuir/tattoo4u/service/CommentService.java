package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Comment;

public interface CommentService {
    Comment save(Comment comment) throws ServiceException;
}

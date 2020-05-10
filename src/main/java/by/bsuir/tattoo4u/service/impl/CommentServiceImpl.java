package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Comment;
import by.bsuir.tattoo4u.repository.CommentRepository;
import by.bsuir.tattoo4u.service.CommentService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) throws ServiceException {
        return commentRepository.save(comment);
    }
}

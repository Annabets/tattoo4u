package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.User;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    User register(User user, String role) throws ServiceException;

    List<User> getAll();
    User getByUsername(String username);
    User getById(Long id);
    List<User> getAllMasters(Pageable pageable);

    User updateById(Long id, User user) throws ServiceException;

    void delete(Long id) throws ServiceException;
}

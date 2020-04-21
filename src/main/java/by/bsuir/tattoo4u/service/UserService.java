package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.User;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    User register(User user, String role) throws ServiceException;
    User addFavourite(String username, Long masterUserId) throws ServiceException;

    User addFavouriteStudio(String username, Long studioId) throws ServiceException;
    User removeFavouriteStudio(String username, Long studioId) throws ServiceException;

    List<User> getAll();
    User getByUsername(String username);
    User getById(Long id);
    List<User> getAllMasters(Pageable pageable);

    User updateById(Long id, User user) throws ServiceException;

    void delete(Long id) throws ServiceException;
    User removeFavourite(String username, Long masterUserId) throws ServiceException;

    void banUser(Long id) throws ServiceException;
    boolean isBanned(String username);
    void unbanUser(Long id) throws ServiceException;

    void save(User user) throws ServiceException;
}

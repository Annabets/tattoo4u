package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    User registerMaster(User user);
    User registerAdmin(User user);

    List<User> getAll();
    User getByUsername(String username);
    User getById(Long id);

    void delete(Long id);
}

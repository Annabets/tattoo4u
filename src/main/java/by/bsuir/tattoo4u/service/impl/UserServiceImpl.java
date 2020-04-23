package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Role;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.RoleRepository;
import by.bsuir.tattoo4u.repository.UserRepository;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.UserService;
import by.bsuir.tattoo4u.service.validator.UserDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User getByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User getById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<User> getAllMasters(Pageable pageable) {
        return userRepository.getAllByRolesContaining(roleRepository.findByName("MASTER"), pageable);
    }

    @Override
    public User updateById(Long id, User user) throws ServiceException {

        User updateUser = userRepository.findById(id).orElse(null);

        if (updateUser == null) {
            throw new ServiceException("No user with (" + id + ") id");
        }

        if (!UserDataValidator.isValidUsername(user)) {
            throw new ServiceException("Incorrect username");
        }

        if (!UserDataValidator.isValidEmail(user)) {
            throw new ServiceException("Incorrect email");
        }

        if (userRepository.existsByUsernameAndIdNot(user.getUsername(), id)) {
            throw new ServiceException("Username is already taken");
        }
        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new ServiceException("Email has already in use");
        }

        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());

        User result = userRepository.save(updateUser);

        return result;
    }

    @Override
    public void delete(Long id) throws ServiceException {
        if (!userRepository.existsById(id)) {
            throw new ServiceException("No such user");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) throws ServiceException {
        userRepository.save(user);
    }

    @Override
    public User register(User user, String roleName) throws ServiceException {

        refactorUser(user, roleName);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ServiceException("Username has already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ServiceException("Email has already in use");
        }

        User registeredUser = userRepository.save(user);

        return registeredUser;
    }

    private void refactorUser(User user, String roleName) throws ServiceException {
        if (!UserDataValidator.isValidUsername(user)) {
            throw new ServiceException("Incorrect username");
        }

        if (!UserDataValidator.isValidEmail(user)) {
            throw new ServiceException("Incorrect email");
        }

        if (!UserDataValidator.isValidPassword(user)) {
            throw new ServiceException("Incorrect password");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        extractRole(user, roleName);
    }

    private void extractRole(User user, String roleName) throws ServiceException {
        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            throw new ServiceException("Incorrect role");
        }

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);

        user.setRoles(userRoles);
    }
}

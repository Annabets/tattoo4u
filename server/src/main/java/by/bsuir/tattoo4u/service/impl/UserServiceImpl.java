package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Role;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.RoleRepository;
import by.bsuir.tattoo4u.repository.UserRepository;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        return register(user, "USER");
    }

    @Override
    public User registerMaster(User user) {
        return register(user, "MASTER");
    }

    @Override
    public User registerAdmin(User user) {
        return register(user, "ADMIN");
    }

    @Override
    public List<User> getAll() {
        List<User> result=userRepository.findAll();
        return result;
    }

    @Override
    public User getByUsername(String username) {
        User result=userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User getById(Long id) {
        User result=userRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private User register(User user, String roleName){
        Role role=roleRepository.findByName(roleName);
        List<Role> userRoles=new ArrayList<>();
        userRoles.add(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser=userRepository.save(user);

        return registeredUser;
    }
}

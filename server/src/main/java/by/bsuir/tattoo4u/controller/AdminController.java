package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.UserDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tattoo4u/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<UserDto>> getUserById() {
        List<User> userList = userService.getAll();

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> userDtoList=new ArrayList<>();
        for (User user:userList){
            userDtoList.add(UserDto.fromUser(user));
        }

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
}

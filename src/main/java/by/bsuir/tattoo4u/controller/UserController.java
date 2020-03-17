package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.RegistrationUserRequestDto;
import by.bsuir.tattoo4u.dto.response.UserResponseDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;
    private final HttpHeaders httpHeaders;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> userList = userService.getAll();

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
        }

        List<UserResponseDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserResponseDto.fromUser(user));
        }

        return new ResponseEntity<>(userDtoList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
        }

        UserResponseDto userDto = UserResponseDto.fromUser(user);

        return new ResponseEntity<>(userDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "{id}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable(name = "id") Long id, @RequestBody RegistrationUserRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        User user = null;

        try {
            user = userService.updateById(id, requestDto.getUser(), requestDto.getRole());
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }

        if (user == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT);
        }

        UserResponseDto userDto = UserResponseDto.fromUser(user);

        return new ResponseEntity<>(userDto, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") Long id) {

        try {
            userService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }

        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }
}

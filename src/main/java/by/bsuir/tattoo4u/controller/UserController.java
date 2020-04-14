package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.UpdateUserRequestDto;
import by.bsuir.tattoo4u.dto.response.UserResponseDto;
import by.bsuir.tattoo4u.dto.response.UserWithRoleResponseDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> userList = userService.getAll();

        if (userList == null || userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserResponseDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserResponseDto.fromUser(user));
        }

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserResponseDto userDto = UserResponseDto.fromUser(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUserById(@RequestHeader("Authorization") String bearerToken, @PathVariable(name = "id") Long id, @RequestBody UpdateUserRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String token = tokenService.clearBearerToken(bearerToken);

        if (tokenService.getUsername(token).equals(userService.getById(id).getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = null;

        try {
            user = userService.updateById(id, requestDto.getUser());
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserResponseDto responseDto = new UserResponseDto(user);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {

        try {
            userService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "currentUser")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String bearerToken) {

        String token = null;
        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            token = bearerToken.substring(7);
        }

        String username = tokenService.getUsername(token);

        User user = userService.getByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserWithRoleResponseDto userDto = new UserWithRoleResponseDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "masters")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllMasters(@PageableDefault(sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {

        List<User> userList = userService.getAllMasters(pageable);

        List<UserResponseDto> userResponseDto = new ArrayList<>();
        for (User user : userList) {
            userResponseDto.add(new UserResponseDto(user));
        }

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}

package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.RegistrationUserRequestDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/signUp")
public class RegistrationUserController {

    private final UserService userService;
    private final HttpHeaders httpHeaders;

    @Autowired
    public RegistrationUserController(UserService userService) {
        this.userService = userService;
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationUserRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        User user = requestDto.getUser();
        String role = requestDto.getRole();

        try {
            User registeredUser = userService.register(user, role);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage());
        }

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
}

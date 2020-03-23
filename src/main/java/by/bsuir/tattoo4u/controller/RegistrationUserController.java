package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.RegistrationUserRequestDto;
import by.bsuir.tattoo4u.dto.response.AuthenticationResponseDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.security.jwt.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpHeaders httpHeaders;

    @Autowired
    public RegistrationUserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody String request) {

        RegistrationUserRequestDto requestDto = RegistrationUserRequestDto.fromJson(request);

        if (requestDto == null) {
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }

        User user = requestDto.getUser();
        String role = requestDto.getRole();

        User registeredUser = null;
        try {
            registeredUser = userService.register(user, role);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

        String token = jwtTokenProvider.createToken(registeredUser.getUsername(), registeredUser.getRoles());

        AuthenticationResponseDto responseDto = AuthenticationResponseDto.fromUserAndToken(registeredUser, token);

        return new ResponseEntity<>(responseDto, httpHeaders, HttpStatus.OK);
    }
}

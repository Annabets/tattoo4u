package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.RegistrationUserRequestDto;
import by.bsuir.tattoo4u.dto.response.AuthenticationResponseDto;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.RoleType;
import by.bsuir.tattoo4u.entity.Token;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.security.jwt.JwtTokenProvider;
import by.bsuir.tattoo4u.service.MasterService;
import by.bsuir.tattoo4u.service.ServiceException;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/signUp")
public class RegistrationUserController {

    private final UserService userService;
    private final MasterService masterService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;

    @Autowired
    public RegistrationUserController(UserService userService, MasterService masterService, JwtTokenProvider jwtTokenProvider, TokenService tokenService) {
        this.userService = userService;
        this.masterService = masterService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Validated RegistrationUserRequestDto requestDto) {

        if (requestDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = requestDto.getUser();
        String role = requestDto.getRole();

        User registeredUser = null;
        if(RoleType.valueOf(role)==RoleType.MASTER){

            Master registeredMaster=null;
            try {
                registeredMaster=masterService.add(user, role);
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }

            registeredUser=registeredMaster.getUser();

        }else {
            try {
                registeredUser = userService.register(user, role);
            } catch (ServiceException e) {
                throw new ControllerException(e);
            }
        }

        String token = jwtTokenProvider.createToken(registeredUser.getUsername(), registeredUser.getRoles());

        tokenService.add(token);

        AuthenticationResponseDto responseDto = AuthenticationResponseDto.fromUserAndToken(registeredUser, token);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

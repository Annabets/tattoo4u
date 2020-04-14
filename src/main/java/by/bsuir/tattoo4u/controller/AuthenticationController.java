package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.AuthenticationRequestDto;
import by.bsuir.tattoo4u.dto.response.AuthenticationResponseDto;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.security.jwt.JwtTokenProvider;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
public class AuthenticationController {

    private final static Logger log = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "signIn")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            //AuthenticationRequestDto requestDto = AuthenticationRequestDto.fromJson(request);

            String username = requestDto.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            User user = userService.getByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            tokenService.add(token);

            AuthenticationResponseDto responseDto = AuthenticationResponseDto.fromUserAndToken(user, token);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("signOut")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String bearerToken) {

        String token = null;
        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            token = bearerToken.substring(7);
        }

        tokenService.delete(token);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

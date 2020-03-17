package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.response.ErrorMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private final HttpHeaders httpHeaders;

    @Autowired
    public ExceptionController() {
        this.httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorMessageDto> handle(final BadCredentialsException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception.getMessage());
        return new ResponseEntity<>(message, httpHeaders, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<ErrorMessageDto> handle(final ControllerException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception.getMessage());
        return new ResponseEntity<>(message, httpHeaders, HttpStatus.BAD_REQUEST);
    }
}

package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.controller.controllerExceptionExtn.EmptyDataException;
import by.bsuir.tattoo4u.controller.controllerExceptionExtn.IncorrectDataInputException;
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

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorMessageDto> handle(final BadCredentialsException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<ErrorMessageDto> handle(final ControllerException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyDataException.class)
    public ResponseEntity<ErrorMessageDto> handle(final EmptyDataException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IncorrectDataInputException.class)
    public ResponseEntity<ErrorMessageDto> handle(final IncorrectDataInputException exception) {
        ErrorMessageDto message = new ErrorMessageDto(exception);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}

package ru.chaos.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.chaos.app.dtos.ErrorDto;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDto> catchNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorDto("NO_SUCH_ELEMENT", e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorDto> catchIllegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(new ErrorDto("ILLEGAL_STATE", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

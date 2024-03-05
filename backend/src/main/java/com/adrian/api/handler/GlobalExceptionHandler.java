package com.adrian.api.handler;

import com.adrian.api.exception.Error;
import com.adrian.api.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserDoesNotExistException(UserNotFoundException e) {
        return new ResponseEntity<>(new Error(404, e.getMessage()), HttpStatus.NOT_FOUND);
    }
}

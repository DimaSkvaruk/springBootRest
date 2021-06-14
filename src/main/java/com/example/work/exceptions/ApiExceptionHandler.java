package com.example.work.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        ExceptionDetails exception = new ExceptionDetails(e.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }
}

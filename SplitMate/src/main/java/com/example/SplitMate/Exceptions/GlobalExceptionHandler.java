package com.example.SplitMate.Exceptions;

import com.example.SplitMate.DTOs.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler({EmailAlreadyRegisteredException.class})
    public ResponseEntity<ExceptionDto> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException emailAlreadyRegisteredException) {

    return new ResponseEntity<>(new ExceptionDto(HttpStatus.FOUND,emailAlreadyRegisteredException.getMessage()),HttpStatus.FOUND);

    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }




}

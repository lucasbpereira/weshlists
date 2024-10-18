package com.weshlists.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ErrorsPatternDto> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorsPatternDto(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsLoginException.class)
    public ResponseEntity<ErrorsPatternDto> handleInvalidCredentialsException(BadCredentialsLoginException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorsPatternDto(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }

    @ExceptionHandler(EmptyImageException.class)
    public ResponseEntity<ErrorsPatternDto> handleInvalidCredentialsException(EmptyImageException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorsPatternDto(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}

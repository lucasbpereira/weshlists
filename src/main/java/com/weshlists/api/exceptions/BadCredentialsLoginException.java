package com.weshlists.api.exceptions;

public class BadCredentialsLoginException extends RuntimeException {
    public BadCredentialsLoginException(String message) {
        super(message);
    }
}

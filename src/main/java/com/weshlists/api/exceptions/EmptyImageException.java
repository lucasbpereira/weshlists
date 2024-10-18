package com.weshlists.api.exceptions;

public class EmptyImageException extends RuntimeException {
    public EmptyImageException(String message) {
        super(message);
    }
}

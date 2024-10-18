package com.weshlists.api.exceptions;

import org.springframework.http.HttpStatus;

public record ErrorsPatternDto(HttpStatus status, String error) {

    public ErrorsPatternDto(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }

}

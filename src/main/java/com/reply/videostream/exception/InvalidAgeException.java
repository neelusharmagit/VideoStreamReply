package com.reply.videostream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(final String message) {
        super(message);
    }
}
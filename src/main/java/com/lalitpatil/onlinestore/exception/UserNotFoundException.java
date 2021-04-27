package com.lalitpatil.onlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(HttpStatus status, @Nullable String reason) {
        super(status, reason);
    }
}

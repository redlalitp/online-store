package com.lalitpatil.onlinestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {
    public ProductNotFoundException(HttpStatus status, @Nullable String reason) {
        super(status, reason);
    }
}

package com.example.exchanger.rest.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Исключение API
 */
@Getter
public abstract class ApiException extends RuntimeException {

    protected ApiException(String message) {
        super(message);
    }

    protected ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus getHttpStatus();
}

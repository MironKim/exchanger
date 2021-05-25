package com.example.exchanger.rest.common.exception;

import com.example.exchanger.rest.common.handling.MessageTemplate;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

    public ConflictException(MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args));
    }

    public ConflictException(Throwable cause, MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args), cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}

package com.example.exchanger.rest.common.exception;

import com.example.exchanger.rest.common.handling.MessageTemplate;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

    public ForbiddenException(MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args));
    }

    public ForbiddenException(Throwable cause, MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args), cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}

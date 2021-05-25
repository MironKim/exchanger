package com.example.exchanger.rest.common.exception;

import com.example.exchanger.rest.common.handling.MessageTemplate;
import org.springframework.http.HttpStatus;

public class ApiNotFoundException extends ApiException {

    public ApiNotFoundException(MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args));
    }

    public ApiNotFoundException(Throwable cause, MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args), cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

package com.example.exchanger.rest.common.exception;

import com.example.exchanger.rest.common.handling.MessageTemplate;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends ApiException {

    public UnprocessableEntityException(MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args));
    }

    public UnprocessableEntityException(Throwable cause, MessageTemplate messageTemplate, Object... args) {
        super(messageTemplate.getText(args), cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}

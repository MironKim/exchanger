package com.example.exchanger.rest.clients.controller;


import com.example.exchanger.rest.common.handling.MessageTemplate;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message implements MessageTemplate {

    CLIENT_NOT_FOUND("Клиент не найден!");

    private final String defaultMessage;

    @Override
    public String getText(Object... args) {
        return new MessageFormat(defaultMessage).format(args);
    }
}

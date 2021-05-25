package com.example.exchanger.rest.wallets.controller;


import com.example.exchanger.rest.common.handling.MessageTemplate;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message implements MessageTemplate {

    WALLET_NOT_FOUND("Кошелек не найден!"),
    INCORRECT_CASH_OUT_AMOUNT("Некорректное количество для обналичивания");

    private final String defaultMessage;

    @Override
    public String getText(Object... args) {
        return new MessageFormat(defaultMessage).format(args);
    }
}

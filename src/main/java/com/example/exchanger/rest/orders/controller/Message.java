package com.example.exchanger.rest.orders.controller;


import com.example.exchanger.rest.common.handling.MessageTemplate;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message implements MessageTemplate {

    WALLET_NOT_FOUND("Кошелек не найден!"),
    ORDER_ALREADY_CONFIRMED("Заказ уже подтвержден!"),
    FORBIDDEN_CONFIRM_OWN_ORDER("Запрещено подтверждать свои заказы!"),
    INCORRECT_WALLET("Некорректный кошелек!"),
    INSUFFICIENT_FUNDS("Недостаточно средств!"),
    CURRENCIES_MUST_DIFFERENT("Валюты должны быть разные!");

    private final String defaultMessage;

    @Override
    public String getText(Object... args) {
        return new MessageFormat(defaultMessage).format(args);
    }
}

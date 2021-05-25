package com.example.exchanger.rest.common.handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MessageWithCode {

    /**
     * Код сообщения
     */
    private final String code;
    /**
     * Текст сообщения
     */
    private final String message;
}

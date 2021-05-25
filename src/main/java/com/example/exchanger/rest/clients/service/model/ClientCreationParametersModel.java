package com.example.exchanger.rest.clients.service.model;

import lombok.Data;

/**
 * Параметры создания клиента
 */
@Data
public class ClientCreationParametersModel {

    /**
     * Имя
     */
    private String username;
    /**
     * Пароль
     */
    private String password;
}


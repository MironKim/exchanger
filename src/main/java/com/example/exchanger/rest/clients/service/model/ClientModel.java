package com.example.exchanger.rest.clients.service.model;

import lombok.Data;

/**
 * Клиент
 */
@Data
public class ClientModel {

    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Имя
     */
    private String username;
}


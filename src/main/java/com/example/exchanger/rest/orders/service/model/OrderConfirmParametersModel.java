package com.example.exchanger.rest.orders.service.model;

import lombok.Data;

/**
 * Параметры для исполнения заказа
 */
@Data
public class OrderConfirmParametersModel {

    /**
     * Идентификатор заказа
     */
    private long orderId;
    /**
     * Идентификатор кошелька
     */
    private long walletId;
}


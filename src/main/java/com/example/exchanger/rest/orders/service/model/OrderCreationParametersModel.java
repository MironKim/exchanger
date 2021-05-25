package com.example.exchanger.rest.orders.service.model;

import com.example.exchanger.rest.common.components.Currency;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Параметры для создания заказа
 */
@Data
public class OrderCreationParametersModel {

    /**
     * Исходная валюта
     */
    private Currency sourceCurrency;
    /**
     * Конечная валюта
     */
    private Currency targetCurrency;
    /**
     * Курс
     */
    private BigDecimal rate;
    /**
     * Тип заказа
     */
    private TypeEnum type;
    /**
     * Количество для обмена
     */
    private BigDecimal amount;
    /**
     * Идентификатор кошелька
     */
    private Long walletId;

    /**
     * Тип заказа
     */
    public enum TypeEnum {
        BUY,
        SALE
    }

}


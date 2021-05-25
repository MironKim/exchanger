package com.example.exchanger.rest.orders.service.model;

import com.example.exchanger.rest.common.components.Currency;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Заказ
 */
@Data
public class OrderModel {

    /**
     * Идентификатор заказа
     */
    private Long id;
    /**
     * Идентификатор клиента создавшего заказ
     */
    private Long clientId;
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
     * Тип заказа
     */
    public enum TypeEnum {
        BUY,
        SALE
    }

}


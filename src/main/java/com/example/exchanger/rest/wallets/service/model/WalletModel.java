package com.example.exchanger.rest.wallets.service.model;

import com.example.exchanger.rest.common.components.Currency;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Кошелек
 */
@Data
public class WalletModel {

    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Идентификатор клиента
     */
    private Long clientId;
    /**
     * Валюта
     */
    private Currency currency;
    /**
     * Количество
     */
    private BigDecimal amount;

}


package com.example.exchanger.rest.wallets.service.model;

import com.example.exchanger.rest.common.components.Currency;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Параметры создания кошелька
 */
@Data
public class WalletCreationParametersModel {

    /**
     * Валюта
     */
    private Currency currency;
    /**
     * Количество
     */
    private BigDecimal amount;
}


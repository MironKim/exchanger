package com.example.exchanger.rest.wallets.service.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Параметры обналичивания кошелька
 */
@Data
public class CashOutWalletParametersModel {

    /**
     * Идентификатор
     */
    private long walletId;
    /**
     * Количество
     */
    private BigDecimal amount;
}


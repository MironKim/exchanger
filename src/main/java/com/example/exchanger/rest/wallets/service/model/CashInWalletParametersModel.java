package com.example.exchanger.rest.wallets.service.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Параметры пополнения кошелька
 */
@Data
public class CashInWalletParametersModel {

    /**
     * Идентификатор
     */
    private long walletId;
    /**
     * Количество
     */
    private BigDecimal amount;
}


package com.example.exchanger.rest.orders.entity;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.common.components.Currency;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 * Заказ
 */
@Data
@Entity
public class OrderEntity {

    private static final long serialVersionUID = 536899456483159411L;
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Клиент создавший заказ
     */
    @ManyToOne
    private ClientEntity clientEntity;
    /**
     * Исходная валюта
     */
    @Enumerated(EnumType.STRING)
    private Currency sourceCurrency;
    /**
     * Конечная валюта
     */
    @Enumerated(EnumType.STRING)
    private Currency targetCurrency;
    /**
     * Курс
     */
    @Column(precision = 20, scale = 10)
    private BigDecimal rate;
    /**
     * Тип заказа
     */
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    /**
     * Количество для обмена
     */
    @Column(precision = 20, scale = 10)
    private BigDecimal amount;
    /**
     * Кошелек
     */
    @ManyToOne
    private WalletEntity walletEntity;
    /**
     * Подтвержден
     */
    private Boolean confirmed = false;

    /**
     * Тип заказа
     */
    public enum TypeEnum {
        BUY,
        SALE
    }

}


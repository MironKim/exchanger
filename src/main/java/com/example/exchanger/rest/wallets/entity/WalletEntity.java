package com.example.exchanger.rest.wallets.entity;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.common.components.Currency;
import java.io.Serializable;
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
 * Кошелек
 */
@Data
@Entity
public class WalletEntity implements Serializable {

    private static final long serialVersionUID = 576899456483159411L;
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Клиент
     */
    @ManyToOne
    private ClientEntity clientEntity;
    /**
     * Валюта
     */
    @Enumerated(EnumType.STRING)
    private Currency currency;
    /**
     * Количество
     */
    @Column(precision = 20, scale = 10)
    private BigDecimal amount;
}


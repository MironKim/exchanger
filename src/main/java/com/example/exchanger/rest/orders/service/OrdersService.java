package com.example.exchanger.rest.orders.service;

import com.example.exchanger.rest.orders.service.exception.CurrenciesMustDifferentException;
import com.example.exchanger.rest.orders.service.exception.ForbiddenConfirmOwnOrderException;
import com.example.exchanger.rest.orders.service.exception.IncorrectWalletException;
import com.example.exchanger.rest.orders.service.exception.InsufficientFundsException;
import com.example.exchanger.rest.orders.service.exception.OrderAlreadyConfirmedException;
import com.example.exchanger.rest.orders.service.model.OrderConfirmParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderCreationParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderModel;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import java.util.List;

public interface OrdersService {

    /**
     * Поиск всех заказов
     *
     * @return список заказов
     */
    List<OrderModel> findAll();

    /**
     * Создание заказа
     *
     * @param parameters параметры
     * @throws CurrenciesMustDifferentException валюты должны быть разные
     * @throws WalletNotFoundException кошелек не найден
     */
    void create(OrderCreationParametersModel parameters);

    /**
     * Подтверждение заказа
     *
     * @param parameters параметры
     * @throws WalletNotFoundException кошелек не найден
     * @throws OrderAlreadyConfirmedException заказ уже подтвержден
     * @throws ForbiddenConfirmOwnOrderException запрещено подтверждать свои заказы
     * @throws IncorrectWalletException некорректный кошелек
     * @throws InsufficientFundsException недостаточно средств
     */
    void confirm(OrderConfirmParametersModel parameters);
}

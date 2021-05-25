package com.example.exchanger.rest.wallets.service;

import com.example.exchanger.rest.wallets.service.exception.IncorrectCashOutAmountException;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import com.example.exchanger.rest.wallets.service.model.CashInWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.CashOutWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletCreationParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletModel;
import java.util.List;

/**
 * Сервис по работе с кошельком
 */
public interface WalletsService {

    /**
     * Пополнение
     *
     * @param parameters параметры
     * @throws WalletNotFoundException Кошелек не найден
     */
    void cashIn(CashInWalletParametersModel parameters);

    /**
     * Обналичивание
     *
     * @param parameters параметры
     * @throws WalletNotFoundException кошелек не найден
     * @throws IncorrectCashOutAmountException некорректное количество для обналичивания
     */
    void cashOut(CashOutWalletParametersModel parameters);

    /**
     * Создание
     *
     * @param parameters параметры
     */
    void create(WalletCreationParametersModel parameters);

    /**
     * Удаление по идентификатору
     *
     * @param walletId идентификатор
     * @throws WalletNotFoundException кошелек не найден
     */
    void deleteById(long walletId);

    /**
     * Поиск всех доступных кошельков для текущего пользователя
     *
     * @return список кошельков
     */
    List<WalletModel> findAll();
}

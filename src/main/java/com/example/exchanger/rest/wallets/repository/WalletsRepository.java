package com.example.exchanger.rest.wallets.repository;


import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.common.components.Currency;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface WalletsRepository extends CrudRepository<WalletEntity, Long> {

    /**
     * Поиск кошелька по идентификатору и клиенту
     *
     * @param clientEntity клиент
     * @param id идентификатор
     * @return кошелек, если найден
     */
    Optional<WalletEntity> findByClientEntityAndId(ClientEntity clientEntity, Long id);

    /**
     * Поиск кошелька по валюте и клиенту
     *
     * @param clientEntity клиент
     * @param currency валюта
     * @return кошелек, если найден
     */
    Optional<WalletEntity> findByClientEntityAndCurrency(ClientEntity clientEntity, Currency currency);

    /**
     * Поиск всех кошельков по клиенту
     *
     * @param clientEntity клиент
     * @return список кошельков
     */
    List<WalletEntity> findAllByClientEntity(ClientEntity clientEntity);

}

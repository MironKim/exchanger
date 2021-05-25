package com.example.exchanger.rest.clients.service;

import com.example.exchanger.rest.clients.service.exception.ClientNotFoundException;
import com.example.exchanger.rest.clients.service.model.ClientCreationParametersModel;
import com.example.exchanger.rest.clients.service.model.ClientModel;
import java.util.List;

public interface ClientsService {

    /**
     * Создание клиента по параметрам
     *
     * @param parameter параметры
     */
    void create(ClientCreationParametersModel parameter);

    /**
     * Удаление по идентификатору
     *
     * @param clientId идентификатор
     * @throws ClientNotFoundException клиент не найден
     */
    void deleteById(long clientId);

    /**
     * Поиск всех клиентов
     *
     * @return все клиенты
     */
    List<ClientModel> findAll();
}

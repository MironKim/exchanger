package com.example.exchanger.rest.clients.repository;


import com.example.exchanger.rest.clients.entity.ClientEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<ClientEntity, Long> {

    /**
     * Поиск клиента по имени
     *
     * @param username имя
     * @return пользователь, если найден
     */
    Optional<ClientEntity> findByUsername(String username);
}

package com.example.exchanger.rest.clients.service;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.clients.entity.ClientEntity.Role;
import com.example.exchanger.rest.clients.repository.ClientsRepository;
import com.example.exchanger.rest.clients.service.exception.ClientNotFoundException;
import com.example.exchanger.rest.clients.service.model.ClientCreationParametersModel;
import com.example.exchanger.rest.clients.service.model.ClientModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {

    private final ClientsModelEntityMapper mapper;
    private final ClientsRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public void create(ClientCreationParametersModel parameter) {
        repository.save(mapper.map(parameter.getUsername(), encoder.encode(parameter.getPassword()), Role.ROLE_USER));
    }

    @Override
    public void deleteById(long clientId) {
        ClientEntity clientEntity = repository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        repository.delete(clientEntity);
    }

    @Override
    public List<ClientModel> findAll() {
        return mapper.map(repository.findAll());
    }
}

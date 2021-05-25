package com.example.exchanger.rest.clients.service;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.clients.entity.ClientEntity.Role;
import com.example.exchanger.rest.clients.repository.ClientsRepository;
import com.example.exchanger.rest.clients.service.exception.ClientNotFoundException;
import com.example.exchanger.rest.clients.service.model.ClientCreationParametersModel;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientsServiceImplTest {

    private static final long CLIENT_ID = 1L;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final Role ROLE = Role.ROLE_USER;

    @Spy
    private ClientsModelEntityMapper mapper = Mappers.getMapper(ClientsModelEntityMapper.class);
    @Mock
    private ClientsRepository repository;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private ClientsServiceImpl service;

    @Test
    void create() {
        when(encoder.encode(PASSWORD)).thenReturn(PASSWORD);

        service.create(getCreationParameters());

        verify(repository).save(mapper.map(USERNAME, PASSWORD, ROLE));
    }

    private ClientCreationParametersModel getCreationParameters() {
        ClientCreationParametersModel parameters = new ClientCreationParametersModel();
        parameters.setUsername(USERNAME);
        parameters.setPassword(PASSWORD);
        return parameters;
    }

    @Test
    void deleteById() {
        ClientEntity clientEntity = getClientEntity();
        when(repository.findById(CLIENT_ID)).thenReturn(Optional.of(clientEntity));

        service.deleteById(CLIENT_ID);

        verify(repository).delete(clientEntity);
    }

    @Test
    void deleteByIdClientNotFoundException() {
        when(repository.findById(CLIENT_ID)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> service.deleteById(CLIENT_ID));
    }

    private ClientEntity getClientEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUsername(USERNAME);
        clientEntity.setRole(ROLE);
        clientEntity.setPassword(PASSWORD);
        clientEntity.setId(CLIENT_ID);
        return clientEntity;
    }

    @Test
    void findAll() {
        List<ClientEntity> clients = Collections.singletonList(getClientEntity());
        when(repository.findAll()).thenReturn(clients);

        assertEquals(mapper.map(clients), service.findAll());
    }
}

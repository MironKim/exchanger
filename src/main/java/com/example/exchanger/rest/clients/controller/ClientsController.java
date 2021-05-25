package com.example.exchanger.rest.clients.controller;

import com.example.exchanger.rest.clients.controller.api.ClientsApi;
import com.example.exchanger.rest.clients.controller.dto.Client;
import com.example.exchanger.rest.clients.controller.dto.ClientCreationParameters;
import com.example.exchanger.rest.clients.service.ClientsService;
import com.example.exchanger.rest.clients.service.exception.ClientNotFoundException;
import com.example.exchanger.rest.common.exception.ApiNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.example.exchanger.rest.clients.controller.Message.CLIENT_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class ClientsController implements ClientsApi {

    private final ClientsDtoModelMapper mapper;
    private final ClientsService service;

    @Override
    public ResponseEntity<Void> create(ClientCreationParameters clientCreationParameter) {
        service.create(mapper.map(clientCreationParameter));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Long clientId) {
        try {
            service.deleteById(clientId);
        } catch (ClientNotFoundException e) {
            throw new ApiNotFoundException(e, CLIENT_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(mapper.map(service.findAll()));
    }
}

package com.example.exchanger.rest.clients.controller;


import com.example.exchanger.rest.clients.controller.dto.Client;
import com.example.exchanger.rest.clients.controller.dto.ClientCreationParameters;
import com.example.exchanger.rest.clients.service.model.ClientCreationParametersModel;
import com.example.exchanger.rest.clients.service.model.ClientModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientsDtoModelMapper {

    ClientCreationParametersModel map(ClientCreationParameters source);

    List<Client> map(List<ClientModel> source);
}

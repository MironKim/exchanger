package com.example.exchanger.rest.clients.service;


import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.clients.entity.ClientEntity.Role;
import com.example.exchanger.rest.clients.service.model.ClientModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientsModelEntityMapper {

    List<ClientModel> map(Iterable<ClientEntity> source);

    ClientEntity map(String username, String password, Role role);
}

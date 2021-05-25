package com.example.exchanger.rest.orders.service;


import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.orders.entity.OrderEntity;
import com.example.exchanger.rest.orders.service.model.OrderCreationParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderModel;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersModelEntityMapper {

    List<OrderModel> map(Iterable<OrderEntity> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amount", source = "source.amount")
    @Mapping(target = "clientEntity", source = "clientEntity")
    @Mapping(target = "walletEntity", source = "walletEntity")
    OrderEntity map(OrderCreationParametersModel source, ClientEntity clientEntity, WalletEntity walletEntity);

}

package com.example.exchanger.rest.wallets.service;


import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import com.example.exchanger.rest.wallets.service.model.WalletCreationParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletsModelEntityMapper {

    @Mapping(target = "clientId", source = "clientEntity.id")
    WalletModel map(WalletEntity source);

    List<WalletModel> map(Iterable<WalletEntity> source);

    @Mapping(target = "id", ignore = true)
    WalletEntity map(WalletCreationParametersModel parameters, ClientEntity clientEntity);
}

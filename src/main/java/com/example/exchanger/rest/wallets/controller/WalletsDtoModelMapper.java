package com.example.exchanger.rest.wallets.controller;


import com.example.exchanger.rest.wallets.controller.dto.CashInWalletParameters;
import com.example.exchanger.rest.wallets.controller.dto.CashOutWalletParameters;
import com.example.exchanger.rest.wallets.controller.dto.Wallet;
import com.example.exchanger.rest.wallets.controller.dto.WalletCreationParameters;
import com.example.exchanger.rest.wallets.service.model.CashInWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.CashOutWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletCreationParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletsDtoModelMapper {

    @Mapping(target = "walletId", source = "walletId")
    @Mapping(target = "amount", source = "parameters.amount")
    CashInWalletParametersModel map(Long walletId, CashInWalletParameters parameters);

    @Mapping(target = "walletId", source = "walletId")
    @Mapping(target = "amount", source = "parameters.amount")
    CashOutWalletParametersModel map(Long walletId, CashOutWalletParameters parameters);

    WalletCreationParametersModel map(WalletCreationParameters source);

    List<Wallet> map(List<WalletModel> source);
}

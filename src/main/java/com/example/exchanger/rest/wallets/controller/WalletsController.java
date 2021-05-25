package com.example.exchanger.rest.wallets.controller;

import com.example.exchanger.rest.common.exception.ApiNotFoundException;
import com.example.exchanger.rest.common.exception.UnprocessableEntityException;
import com.example.exchanger.rest.wallets.controller.api.WalletsApi;
import com.example.exchanger.rest.wallets.controller.dto.CashInWalletParameters;
import com.example.exchanger.rest.wallets.controller.dto.CashOutWalletParameters;
import com.example.exchanger.rest.wallets.controller.dto.Wallet;
import com.example.exchanger.rest.wallets.controller.dto.WalletCreationParameters;
import com.example.exchanger.rest.wallets.service.WalletsService;
import com.example.exchanger.rest.wallets.service.exception.IncorrectCashOutAmountException;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.example.exchanger.rest.wallets.controller.Message.INCORRECT_CASH_OUT_AMOUNT;
import static com.example.exchanger.rest.wallets.controller.Message.WALLET_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class WalletsController implements WalletsApi {

    private final WalletsDtoModelMapper mapper;
    private final WalletsService service;

    @Override
    public ResponseEntity<Void> cashIn(Long walletId, CashInWalletParameters cashInWalletParameters) {
        try {
            service.cashIn(mapper.map(walletId, cashInWalletParameters));
        } catch (WalletNotFoundException e) {
            throw new ApiNotFoundException(e, WALLET_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> cashOut(Long walletId, CashOutWalletParameters cashOutWalletParameters) {
        try {
            service.cashOut(mapper.map(walletId, cashOutWalletParameters));
        } catch (WalletNotFoundException e) {
            throw new ApiNotFoundException(e, WALLET_NOT_FOUND);
        } catch (IncorrectCashOutAmountException e) {
            throw new UnprocessableEntityException(e, INCORRECT_CASH_OUT_AMOUNT);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> create(WalletCreationParameters walletCreationParameters) {
        service.create(mapper.map(walletCreationParameters));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteById(Long walletId) {
        try {
            service.deleteById(walletId);
        } catch (WalletNotFoundException e) {
            throw new ApiNotFoundException(e, WALLET_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Wallet>> findAll() {
        return ResponseEntity.ok(mapper.map(service.findAll()));
    }
}

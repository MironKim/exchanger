package com.example.exchanger.rest.orders.controller;

import com.example.exchanger.rest.common.exception.ApiNotFoundException;
import com.example.exchanger.rest.common.exception.ConflictException;
import com.example.exchanger.rest.common.exception.ForbiddenException;
import com.example.exchanger.rest.common.exception.UnprocessableEntityException;
import com.example.exchanger.rest.orders.controller.api.OrdersApi;
import com.example.exchanger.rest.orders.controller.dto.Order;
import com.example.exchanger.rest.orders.controller.dto.OrderConfirmParameters;
import com.example.exchanger.rest.orders.controller.dto.OrderCreationParameters;
import com.example.exchanger.rest.orders.service.OrdersService;
import com.example.exchanger.rest.orders.service.exception.CurrenciesMustDifferentException;
import com.example.exchanger.rest.orders.service.exception.ForbiddenConfirmOwnOrderException;
import com.example.exchanger.rest.orders.service.exception.IncorrectWalletException;
import com.example.exchanger.rest.orders.service.exception.InsufficientFundsException;
import com.example.exchanger.rest.orders.service.exception.OrderAlreadyConfirmedException;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.example.exchanger.rest.orders.controller.Message.CURRENCIES_MUST_DIFFERENT;
import static com.example.exchanger.rest.orders.controller.Message.FORBIDDEN_CONFIRM_OWN_ORDER;
import static com.example.exchanger.rest.orders.controller.Message.INCORRECT_WALLET;
import static com.example.exchanger.rest.orders.controller.Message.INSUFFICIENT_FUNDS;
import static com.example.exchanger.rest.orders.controller.Message.ORDER_ALREADY_CONFIRMED;
import static com.example.exchanger.rest.orders.controller.Message.WALLET_NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class OrdersController implements OrdersApi {

    private final OrdersDtoModelMapper mapper;
    private final OrdersService service;

    @Override
    public ResponseEntity<Void> confirmById(Long orderId, OrderConfirmParameters parameters) {
        try {
            service.confirm(mapper.map(orderId, parameters));
        } catch (WalletNotFoundException e) {
            throw new ApiNotFoundException(e, WALLET_NOT_FOUND);
        } catch (OrderAlreadyConfirmedException e) {
            throw new ConflictException(e, ORDER_ALREADY_CONFIRMED);
        } catch (ForbiddenConfirmOwnOrderException e) {
            throw new ForbiddenException(e, FORBIDDEN_CONFIRM_OWN_ORDER);
        } catch (IncorrectWalletException e) {
            throw new UnprocessableEntityException(e, INCORRECT_WALLET);
        } catch (InsufficientFundsException e) {
            throw new ConflictException(e, INSUFFICIENT_FUNDS);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> create(OrderCreationParameters parameters) {
        try {
            service.create(mapper.map(parameters));
        } catch (CurrenciesMustDifferentException e) {
            throw new ForbiddenException(e, CURRENCIES_MUST_DIFFERENT);
        } catch (WalletNotFoundException e) {
            throw new ApiNotFoundException(e, WALLET_NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(mapper.map(service.findAll()));
    }
}

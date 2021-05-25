package com.example.exchanger.rest.orders.service;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.common.components.Currency;
import com.example.exchanger.rest.common.exception.InternalServerException;
import com.example.exchanger.rest.orders.entity.OrderEntity;
import com.example.exchanger.rest.orders.repository.OrdersRepository;
import com.example.exchanger.rest.orders.service.exception.CurrenciesMustDifferentException;
import com.example.exchanger.rest.orders.service.exception.ForbiddenConfirmOwnOrderException;
import com.example.exchanger.rest.orders.service.exception.IncorrectWalletException;
import com.example.exchanger.rest.orders.service.exception.InsufficientFundsException;
import com.example.exchanger.rest.orders.service.exception.OrderAlreadyConfirmedException;
import com.example.exchanger.rest.orders.service.exception.OrderNotFoundException;
import com.example.exchanger.rest.orders.service.model.OrderConfirmParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderCreationParametersModel;
import com.example.exchanger.rest.orders.service.model.OrderModel;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import com.example.exchanger.rest.wallets.repository.WalletsRepository;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersModelEntityMapper mapper;
    private final OrdersRepository repository;
    private final WalletsRepository walletsRepository;

    @Override
    public List<OrderModel> findAll() {
        return mapper.map(repository.findAll());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void create(OrderCreationParametersModel parameters) {
        if (parameters.getSourceCurrency() == parameters.getTargetCurrency()) {
            throw new CurrenciesMustDifferentException();
        }
        WalletEntity wallet = findWalletByIdForCurrentClient(parameters.getWalletId());
        repository.save(mapper.map(parameters, getCurrentClient(), wallet));
    }

    private WalletEntity findWalletByIdForCurrentClient(long walletId) {
        return walletsRepository.findByClientEntityAndId(getCurrentClient(), walletId)
                .orElseThrow(WalletNotFoundException::new);
    }

    private ClientEntity getCurrentClient() {
        return (ClientEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void confirm(OrderConfirmParametersModel parameters) {
        OrderEntity order = repository.findById(parameters.getOrderId()).orElseThrow(OrderNotFoundException::new);
        ClientEntity currentClient = getCurrentClient();
        WalletEntity currentClientWallet = findWalletByIdForCurrentClient(parameters.getWalletId());
        ClientEntity orderClient = order.getClientEntity();
        WalletEntity orderClientWallet = order.getWalletEntity();

        validateOrder(order, currentClient, orderClient);

        switch (order.getType()) {
            case BUY:
                exchange(order, orderClient, orderClientWallet, currentClient, currentClientWallet);
                break;
            case SALE:
                exchange(order, currentClient, currentClientWallet, orderClient, orderClientWallet);
                break;
            default:
                throw new InternalServerException("Неизвестный тип заказа : " + order.getType());
        }

        order.setConfirmed(true);
    }

    private void validateOrder(OrderEntity order, ClientEntity currentClient, ClientEntity orderClient) {
        if (order.getConfirmed()) {
            throw new OrderAlreadyConfirmedException();
        }
        if (orderClient.getId().equals(currentClient.getId())) {
            throw new ForbiddenConfirmOwnOrderException();
        }
    }

    private void exchange(OrderEntity order, ClientEntity buyer, WalletEntity buyerSourceWallet,
                          ClientEntity seller, WalletEntity sellerTargetWallet) {
        Currency sourceCurrency = order.getSourceCurrency();
        Currency targetCurrency = order.getTargetCurrency();
        BigDecimal amountInTargetCurrency = order.getAmount();
        BigDecimal rate = order.getRate();
        BigDecimal amountInSourceCurrency = amountInTargetCurrency.multiply(rate);

        WalletEntity buyerTargetWallet = getWalletByClientAndCurrency(targetCurrency, buyer);
        WalletEntity sellerSourceWallet = getWalletByClientAndCurrency(sourceCurrency, seller);

        validateCorrectWalletCurrency(buyerSourceWallet, sourceCurrency);
        validateCorrectWalletCurrency(sellerTargetWallet, targetCurrency);

        buyerSourceWallet.setAmount(buyerSourceWallet.getAmount().subtract(amountInSourceCurrency));
        validateResidualBalance(buyerSourceWallet);
        buyerTargetWallet.setAmount(buyerTargetWallet.getAmount().add(amountInTargetCurrency));

        sellerSourceWallet.setAmount(sellerSourceWallet.getAmount().add(amountInSourceCurrency));
        sellerTargetWallet.setAmount(sellerTargetWallet.getAmount().subtract(amountInTargetCurrency));
        validateResidualBalance(sellerTargetWallet);
    }

    private void validateCorrectWalletCurrency(WalletEntity wallet, Currency currency) {
        if (wallet.getCurrency() != currency) {
            throw new IncorrectWalletException();
        }
    }

    private WalletEntity getWalletByClientAndCurrency(Currency targetCurrency, ClientEntity seller) {
        return walletsRepository.findByClientEntityAndCurrency(seller, targetCurrency)
                .orElseThrow(WalletNotFoundException::new);
    }

    private void validateResidualBalance(WalletEntity wallet) {
        if (wallet.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
    }
}

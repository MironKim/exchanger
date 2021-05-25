package com.example.exchanger.rest.wallets.service;

import com.example.exchanger.rest.clients.entity.ClientEntity;
import com.example.exchanger.rest.wallets.entity.WalletEntity;
import com.example.exchanger.rest.wallets.repository.WalletsRepository;
import com.example.exchanger.rest.wallets.service.exception.IncorrectCashOutAmountException;
import com.example.exchanger.rest.wallets.service.exception.WalletNotFoundException;
import com.example.exchanger.rest.wallets.service.model.CashInWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.CashOutWalletParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletCreationParametersModel;
import com.example.exchanger.rest.wallets.service.model.WalletModel;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletsServiceImpl implements WalletsService {

    private final WalletsModelEntityMapper mapper;
    private final WalletsRepository repository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void cashIn(CashInWalletParametersModel parameters) {
        WalletEntity walletEntity = findByIdAndCurrentClient(parameters.getWalletId());
        BigDecimal currentAmount = walletEntity.getAmount();
        walletEntity.setAmount(currentAmount.add(parameters.getAmount()));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void cashOut(CashOutWalletParametersModel parameters) {
        WalletEntity walletEntity = findByIdAndCurrentClient(parameters.getWalletId());
        BigDecimal currentAmount = walletEntity.getAmount();
        if (currentAmount.compareTo(parameters.getAmount()) < 0) {
            throw new IncorrectCashOutAmountException();
        }
        walletEntity.setAmount(currentAmount.subtract(parameters.getAmount()));
    }

    private WalletEntity findByIdAndCurrentClient(long walletId) {
        return repository.findByClientEntityAndId(getCurrentClient(), walletId)
                .orElseThrow(WalletNotFoundException::new);
    }

    @Override
    public void create(WalletCreationParametersModel parameters) {
        repository.save(mapper.map(parameters, getCurrentClient()));
    }

    private ClientEntity getCurrentClient() {
        return (ClientEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteById(long walletId) {
        repository.delete(findByIdAndCurrentClient(walletId));
    }

    @Override
    public List<WalletModel> findAll() {
        return mapper.map(repository.findAllByClientEntity(getCurrentClient()));
    }
}

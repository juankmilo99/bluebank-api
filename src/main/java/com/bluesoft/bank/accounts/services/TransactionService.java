package com.bluesoft.bank.accounts.services;

import com.bluesoft.bank.accounts.dtos.inputs.MovementRequest;
import com.bluesoft.bank.accounts.entities.Account;
import com.bluesoft.bank.accounts.entities.City;
import com.bluesoft.bank.accounts.entities.Movement;
import com.bluesoft.bank.accounts.exceptions.InsufficientBalanceException;
import com.bluesoft.bank.accounts.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CityService cityService;

    @Autowired
    private MovementRepository movementRepository;

    /*public BalanceResponse getBalance(BalanceRequest request) {
        // Implementar lógica de validación y obtención del saldo
        // ...
        return new BalanceResponse(true, balance);
    }*/

    public void makeDeposit(MovementRequest request) {
        City city = cityService.validateCityExist(request.getCityName());
        Account account = accountService.validateClientAndAccount(request.getDocumentNumber(), request.getAccountNumber());

        BigDecimal amount = request.getAmount();
        account.setBalance(account.getBalance().add(amount));

        Movement deposit = new Movement("Deposit", amount, LocalDateTime.now(), "Complete", account, city);
        movementRepository.save(deposit);

        accountService.save(account);
    }

    public void makeWithdrawal(MovementRequest request) {
        City city = cityService.validateCityExist(request.getCityName());
        Account account = accountService.validateClientAndAccount(request.getDocumentNumber(), request.getAccountNumber());

        BigDecimal amount = request.getAmount();
        Movement withdrawal = new Movement("Withdrawal", amount, LocalDateTime.now(), "Complete", account, city);

        validateSufficientBalance(withdrawal);

        account.setBalance(account.getBalance().subtract(amount));

        movementRepository.save(withdrawal);

        accountService.save(account);
    }

    private void validateSufficientBalance(Movement withdrawal) {
        if (!withdrawal.haveSufficientBalance()) {
            withdrawal.setStatus("Reject");
            movementRepository.save(withdrawal);
            throw new InsufficientBalanceException("Insufficient funds for withdrawal");
        }
    }
}

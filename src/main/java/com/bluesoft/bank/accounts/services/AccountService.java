package com.bluesoft.bank.accounts.services;

import com.bluesoft.bank.accounts.dtos.outputs.BalanceResponse;
import com.bluesoft.bank.accounts.entities.Account;
import com.bluesoft.bank.accounts.entities.Client;
import com.bluesoft.bank.accounts.exceptions.EntityNotFoundException;
import com.bluesoft.bank.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountRepository accountRepository;

    public Account validateClientAndAccount(String documentNumber, Long accountNumber) {
        Client client = clientService.validateClientExist(documentNumber);

        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));

        if (!account.getClient().equals(client)) {
            throw new IllegalArgumentException("La cuenta no pertenece al cliente especificado");
        }

        return account;
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public BalanceResponse getBalanceByAccountAndDocument(Long accountNumber, String documentNumber) {
        Account account = accountRepository
                .findByIdAndClientIdentifier(accountNumber, documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró ninguna cuenta para el número de cuenta y el número de documento especificados."));

        return new BalanceResponse(account.getBalance());
    }

}

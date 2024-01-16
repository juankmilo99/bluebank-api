package com.bluesoft.bank.accounts.services;

import com.bluesoft.bank.accounts.dtos.outputs.ClientTransactionCountDto;
import com.bluesoft.bank.accounts.dtos.outputs.ClientWithdrawalDto;
import com.bluesoft.bank.accounts.entities.Client;
import com.bluesoft.bank.accounts.exceptions.EntityNotFoundException;
import com.bluesoft.bank.accounts.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client validateClientExist(String documentNumber) {
        return clientRepository.findByIdentifier(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    public List<ClientTransactionCountDto> getClientTransactionCountsForMonth(int year, int month) {
        List<Object[]> clientTransactionCounts = clientRepository.findClientTransactionCountsForMonth(year, month);

        return clientTransactionCounts.stream()
                .map(result -> {
                    Client client = (Client) result[0];
                    Long transactionCount = (Long) result[1];
                    return new ClientTransactionCountDto(client.getIdentifier(), client.getName(),
                            client.getClientType(), transactionCount);
                })
                .collect(Collectors.toList());
    }

    public List<ClientWithdrawalDto> getClientsWithLargeWithdrawals() {
        List<Object[]> clientsWithLargeWithdrawals = clientRepository.findClientsWithLargeWithdrawals();

        return clientsWithLargeWithdrawals.stream()
                .map(result -> {
                    Client client = (Client) result[0];
                    BigDecimal totalWithdrawalAmount = (BigDecimal) result[1];
                    return new ClientWithdrawalDto(client.getIdentifier(), client.getName(),
                            client.getClientType(), totalWithdrawalAmount);
                })
                .collect(Collectors.toList());
    }

}

package com.bluesoft.bank.accounts.services;

import com.bluesoft.bank.accounts.dtos.inputs.BalanceRequest;
import com.bluesoft.bank.accounts.dtos.outputs.BalanceResponse;
import com.bluesoft.bank.accounts.dtos.outputs.MovementDto;
import com.bluesoft.bank.accounts.entities.Account;
import com.bluesoft.bank.accounts.entities.Client;
import com.bluesoft.bank.accounts.entities.Movement;
import com.bluesoft.bank.accounts.exceptions.EntityNotFoundException;
import com.bluesoft.bank.accounts.repositories.AccountRepository;
import com.bluesoft.bank.accounts.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private MovementRepository movementRepository;

    public List<MovementDto> getRecentMovementsByDocumentNumber(String documentNumber) {
        clientService.validateClientExist(documentNumber);

        List<Movement> movements = movementRepository
                .findTop20ByAccountClientIdentifierAndStatusOrderByDateTimeDesc(documentNumber, "Complete");

        return movements.stream()
                .map(movement -> new MovementDto(
                        movement.getDateTime(),
                        movement.getAccount().getId(),
                        movement.getAmount(),
                        movement.getType()))
                .collect(Collectors.toList());
    }

    public List<MovementDto> getMonthlyStatementByDocumentNumber(String documentNumber, int year, int month) {
        clientService.validateClientExist(documentNumber);

        List<Movement> movements = movementRepository.findByAccountClientIdentifierAndStatusAndYearAndMonth(
                documentNumber, year, month);

        return movements.stream()
                .map(movement -> new MovementDto(
                        movement.getDateTime(),
                        movement.getAccount().getId(),
                        movement.getAmount(),
                        movement.getType()))
                .collect(Collectors.toList());
    }

}

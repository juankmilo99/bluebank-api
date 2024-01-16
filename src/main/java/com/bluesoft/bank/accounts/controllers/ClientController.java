package com.bluesoft.bank.accounts.controllers;

import com.bluesoft.bank.accounts.dtos.outputs.ClientTransactionCountDto;
import com.bluesoft.bank.accounts.dtos.outputs.ClientWithdrawalDto;
import com.bluesoft.bank.accounts.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/max-transactions")
    public ResponseEntity<List<ClientTransactionCountDto>> maxTransactions(@RequestParam int month,
                                                                           @RequestParam int year) {
        List<ClientTransactionCountDto> list = clientService.getClientTransactionCountsForMonth(year, month);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/other-city")
    public ResponseEntity<List<ClientWithdrawalDto>> otherCity() {
        List<ClientWithdrawalDto> list = clientService.getClientsWithLargeWithdrawals();
        return ResponseEntity.ok(list);
    }

}


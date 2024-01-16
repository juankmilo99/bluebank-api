package com.bluesoft.bank.accounts.controllers;

import com.bluesoft.bank.accounts.dtos.outputs.BalanceResponse;
import com.bluesoft.bank.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponse> makeDeposit(@RequestParam String documentNumber,
                                                       @RequestParam Long accountNumber) {
        BalanceResponse balance = accountService.getBalanceByAccountAndDocument(accountNumber, documentNumber);
        return ResponseEntity.ok(balance);
    }

}


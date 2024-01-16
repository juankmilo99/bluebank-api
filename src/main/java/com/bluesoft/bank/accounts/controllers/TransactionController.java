package com.bluesoft.bank.accounts.controllers;

import com.bluesoft.bank.accounts.dtos.inputs.MovementRequest;
import com.bluesoft.bank.accounts.dtos.outputs.SuccessResponse;
import com.bluesoft.bank.accounts.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<SuccessResponse> makeDeposit(@RequestBody MovementRequest request) {
        transactionService.makeDeposit(request);
        return ResponseEntity.ok(new SuccessResponse(true, "Deposit successful"));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<SuccessResponse> makeWithdrawal(@RequestBody MovementRequest request) {
        transactionService.makeWithdrawal(request);
        return ResponseEntity.ok(new SuccessResponse(true, "Withdrawal successful"));
    }
}


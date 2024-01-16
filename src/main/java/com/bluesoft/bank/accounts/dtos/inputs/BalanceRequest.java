package com.bluesoft.bank.accounts.dtos.inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceRequest {
    private String documentNumber;
    private Long accountNumber;
}
package com.bluesoft.bank.accounts.dtos.inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovementRequest {
    private String documentNumber;
    private Long accountNumber;
    private BigDecimal amount;
    private String cityName;
}
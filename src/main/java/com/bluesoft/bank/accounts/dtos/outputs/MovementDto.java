package com.bluesoft.bank.accounts.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MovementDto {
    private LocalDateTime dateTime;
    private Long accountNumber;
    private BigDecimal amount;
    private String type;
}

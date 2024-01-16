package com.bluesoft.bank.accounts.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithdrawalDto {
    private String identifier;
    private String clientName;
    private String clientType;
    private BigDecimal totalWithdrawalAmount;
}

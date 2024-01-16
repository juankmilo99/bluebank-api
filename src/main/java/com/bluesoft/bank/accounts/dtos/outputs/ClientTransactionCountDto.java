package com.bluesoft.bank.accounts.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientTransactionCountDto {
    private String identifier;
    private String clientName;
    private String clientType;
    private Long transactionCount;
}

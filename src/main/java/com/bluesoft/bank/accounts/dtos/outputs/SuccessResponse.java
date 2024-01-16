package com.bluesoft.bank.accounts.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse {
    private boolean success;
    private String message;

}
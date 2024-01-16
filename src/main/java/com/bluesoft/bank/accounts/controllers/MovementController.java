package com.bluesoft.bank.accounts.controllers;

import com.bluesoft.bank.accounts.dtos.outputs.MovementDto;
import com.bluesoft.bank.accounts.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping("/recent")
    public ResponseEntity<List<MovementDto>> getRecentMovementsByDocumentNumber(@RequestParam String documentNumber) {
        List<MovementDto> movementDTOs = movementService.getRecentMovementsByDocumentNumber(documentNumber);
        return new ResponseEntity<>(movementDTOs, HttpStatus.OK);
    }

    @GetMapping("/statement")
    public ResponseEntity<List<MovementDto>> getMonthlyStatement(@RequestParam String documentNumber,
                                                                 @RequestParam int month,
                                                                 @RequestParam int year) {
        List<MovementDto> movementDTOs = movementService.getMonthlyStatementByDocumentNumber(
                documentNumber, year, month);
        return new ResponseEntity<>(movementDTOs, HttpStatus.OK);
    }
}

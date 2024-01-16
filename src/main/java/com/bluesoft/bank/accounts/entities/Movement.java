package com.bluesoft.bank.accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movements")
@NoArgsConstructor
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Movement(String type, BigDecimal amount, LocalDateTime dateTime, String status, Account account, City city) {
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.status = status;
        this.account = account;
        this.city = city;
    }

    public boolean haveSufficientBalance() {
        return getAccount().haveSufficientBalance(getAmount());
    }
}
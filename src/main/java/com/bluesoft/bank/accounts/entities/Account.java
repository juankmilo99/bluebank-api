package com.bluesoft.bank.accounts.entities;

import lombok.Data;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public boolean haveSufficientBalance(BigDecimal amount) {
        return getBalance().compareTo(amount) >= 0;
    }
}
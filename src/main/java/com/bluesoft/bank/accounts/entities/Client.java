package com.bluesoft.bank.accounts.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "client_type", nullable = false)
    private String clientType;

    @Column(name = "identifier", nullable = false)
    private String identifier;
}
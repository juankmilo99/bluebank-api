package com.bluesoft.bank.accounts.entities;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
package com.bluesoft.bank.accounts.repositories;

import com.bluesoft.bank.accounts.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}

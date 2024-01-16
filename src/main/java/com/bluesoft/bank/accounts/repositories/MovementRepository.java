package com.bluesoft.bank.accounts.repositories;

import com.bluesoft.bank.accounts.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findTop20ByAccountClientIdentifierAndStatusOrderByDateTimeDesc(String documentNumber, String status);

    @Query("SELECT m FROM Movement m " +
            "WHERE m.account.client.identifier = :documentNumber " +
            "AND m.status = 'Complete' " +
            "AND YEAR(m.dateTime) = :year " +
            "AND MONTH(m.dateTime) = :month")
    List<Movement> findByAccountClientIdentifierAndStatusAndYearAndMonth(
            @Param("documentNumber") String documentNumber,
            @Param("year") int year,
            @Param("month") int month);
}


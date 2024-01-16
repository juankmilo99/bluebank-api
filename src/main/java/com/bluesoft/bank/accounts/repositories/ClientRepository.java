package com.bluesoft.bank.accounts.repositories;

import com.bluesoft.bank.accounts.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdentifier(String documentNumber);

    @Query("SELECT m.account.client, COUNT(m) " +
            "FROM Movement m " +
            "WHERE YEAR(m.dateTime) = :year " +
            "AND MONTH(m.dateTime) = :month " +
            "GROUP BY m.account.client " +
            "ORDER BY COUNT(m) DESC")
    List<Object[]> findClientTransactionCountsForMonth(
            @Param("year") int year,
            @Param("month") int month);

    @Query("SELECT m.account.client, SUM(m.amount) " +
            "FROM Movement m " +
            "WHERE m.type = 'Withdrawal' " +
            "AND m.city != m.account.city " +
            "GROUP BY m.account.client " +
            "HAVING SUM(m.amount) > 1000000")
    List<Object[]> findClientsWithLargeWithdrawals();
}

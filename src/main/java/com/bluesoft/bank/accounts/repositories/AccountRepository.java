package com.bluesoft.bank.accounts.repositories;

import com.bluesoft.bank.accounts.entities.Account;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(@NotNull  Long accountNumber);

    Optional<Account> findByIdAndClientIdentifier(Long accountNumber, String identifier);
}

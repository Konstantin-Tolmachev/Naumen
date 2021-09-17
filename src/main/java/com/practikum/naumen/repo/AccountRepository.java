package com.practikum.naumen.repo;

import com.practikum.naumen.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername (String username);
}
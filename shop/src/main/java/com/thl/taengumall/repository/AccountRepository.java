package com.thl.taengumall.repository;

import com.thl.taengumall.jpa.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);

}
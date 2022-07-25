package com.thl.taengumall.service;

import com.thl.taengumall.jpa.Account;
import com.thl.taengumall.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    @NotNull
    private final AccountRepository repository;

    public List<Account> getAccounts() {
        var accounts = repository.findAll();
        return accounts;
    }

    public Account getAccountByEmail(String email) {
        var account = repository.findByEmail(email);
        return account;
    }

    public void createAccount(Account account) {
        repository.save(account);
    }

    public void updateAccount(Account account) {
        var user = repository.getReferenceById(account.getId());
        user.setName(account.getName());
        user.setAddress(account.getAddress());
        user.setPassword(account.getPassword());
    }
}

package com.acmesoft.atm.services;

import com.acmesoft.atm.model.Account;

public interface AccountService {
    void save(Account account);
    void deposit(Long accountId);
    void withdraw(Long accountId);
    void transfer(Long accountId, Long destinationAccountId);
    Account findById(Long accountId);
}

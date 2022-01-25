package com.acmesoft.atm.dao;

import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;

public interface AccountDao {
    void save(Account account);
    GenericResponse deposit(Long accountId);
    void withdraw(Long accountId);
    void transfer(Long accountId, Long destinationAccountId);
    void balance(Long accountId);
    void movements(Long accountId);
    Account findById(Long accountId);
}

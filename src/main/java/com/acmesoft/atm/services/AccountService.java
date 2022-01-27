package com.acmesoft.atm.services;

import com.acmesoft.atm.model.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    Account update(Account account);

    Account deposit(Account account);

    List<Account> getAll();

    Account findById(long accountId);

}

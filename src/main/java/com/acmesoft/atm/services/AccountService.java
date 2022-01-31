package com.acmesoft.atm.services;

import com.acmesoft.atm.model.Account;

import java.util.List;

public interface AccountService<S> {

    S save(S account);

    S update(S account);

    S deposit(S account);

    List<S> getAll();

    S findById(long accountId);

    void delete(long accountId);

    List<S> transfer(S originId, S destinationId, double amount);

}

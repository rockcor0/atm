package com.acmesoft.atm.dao;

import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImp implements AccountDao{

    @Override
    public void save(Account account) {

    }

    @Override
    public GenericResponse deposit(Long accountId) {
        return new GenericResponse(true, "Operacion Exitosa");
    }

    @Override
    public void withdraw(Long accountId) {

    }

    @Override
    public void transfer(Long accountId, Long destinationAccountId) {

    }

    @Override
    public void balance(Long accountId) {

    }

    @Override
    public void movements(Long accountId) {

    }

    @Override
    public Account findById(Long accountId) {
        return null;
    }
}

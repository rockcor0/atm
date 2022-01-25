package com.acmesoft.atm.services;

import com.acmesoft.atm.dao.AccountDao;
import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImp implements AccountService{

    @Autowired
    private AccountDao _accountDao;

    @Override
    public void save(Account account) {

    }

    @Override
    public GenericResponse deposit(Long accountId) {
        return _accountDao.deposit(accountId);
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

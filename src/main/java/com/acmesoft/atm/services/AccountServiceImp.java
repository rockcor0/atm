package com.acmesoft.atm.services;

import com.acmesoft.atm.constants.GenericMessage;
import com.acmesoft.atm.exception.AccountNotFoundException;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("accountService")
@Transactional
public class AccountServiceImp implements AccountService{

    @Autowired
    private AccountRepository _accountRepository;

    @Override
    public Account save(Account account) {
        return _accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {

        Optional<Account> accountInDB = this._accountRepository.findById(account.getId());

        if(accountInDB.isPresent()){
            Account accountForUpdate = accountInDB.get();
            accountForUpdate.setId(account.getId());
            accountForUpdate.setName(account.getName());

            return accountForUpdate;
        }

        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public Account deposit(Account account) {

        Optional<Account> accountInDB = this._accountRepository.findById(account.getId());

        if(accountInDB.isPresent()){
            Account accountForUpdate = accountInDB.get();
            accountForUpdate.setBalance(account.getBalance());

            return accountForUpdate;
        }

        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public List<Account> getAll() {
        return _accountRepository.findAll();
    }

    @Override
    public Account findById(long accountId) {
        Optional<Account> accountInDB = this._accountRepository.findById(accountId);

        if(accountInDB.isPresent()){
            return accountInDB.get();
        }

        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }
}

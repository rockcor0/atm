package com.acmesoft.atm.services;

import com.acmesoft.atm.constants.GenericMessage;
import com.acmesoft.atm.constants.Values;
import com.acmesoft.atm.exception.AccountNotFoundException;
import com.acmesoft.atm.exception.NegativeBalanceException;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.model.Operation;
import com.acmesoft.atm.model.OperationDirection;
import com.acmesoft.atm.model.OperationType;
import com.acmesoft.atm.repository.AccountRepository;
import com.acmesoft.atm.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("accountService")
@Transactional
public class AccountServiceImp implements AccountService<Account> {

    @Autowired
    private AccountRepository _accountRepository;

    @Autowired
    private OperationService _operationService;

    @Override
    public Account save(Account account) {
        return _accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {

        Optional<Account> accountInDB = this._accountRepository.findById(account.getId());

        if (accountInDB.isPresent()) {
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
        double amount = account.getBalance();

        if (accountInDB.isPresent()) {
            Account accountForUpdate = accountInDB.get();
            double newBalance = calculateNewBalance(accountForUpdate.getBalance(), amount);
            accountForUpdate.setBalance(newBalance);

            saveReport(amount, accountForUpdate.getId(), null);

            return accountForUpdate;
        }
        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }

    private void saveReport(double amount, long accountOwnerId, Account originOrDestinationDefined) {
        Operation reportToSave = new Operation();

        Optional<Account> originOrDestinationTemp = originOrDestinationDefined == null
                ? this._accountRepository.findById(Values.DEFAULT_ATM_ID)
                : Optional.of(originOrDestinationDefined);

        String accountName = "";

        if (originOrDestinationTemp.isPresent()) {
            accountName = originOrDestinationTemp.get().getName();
            reportToSave.setOriginOrDestinAccountId(originOrDestinationTemp.get().getId());
        } else {
            Account atm = createAtm();
            accountName = atm.getName();
            Account accountInDB = this._accountRepository.save(atm);
            reportToSave.setOriginOrDestinAccountId(accountInDB.getId());
        }

        if (amount > 0) {
            reportToSave.setType(OperationType.DEPOSIT);
            reportToSave.setDirection(OperationDirection.INPUT);
            reportToSave.setDescription("Depósito desde " + accountName + " por valor de " + amount);
        } else {
            reportToSave.setType(OperationType.WITHDRAW);
            reportToSave.setDirection(OperationDirection.OUTPUT);
            reportToSave.setDescription("Retiro desde " + accountName + " por valor de " + amount);
        }

        reportToSave.setAmount(amount);
        reportToSave.setAccountId(accountOwnerId);

        this._operationService.save(reportToSave);
    }

    private Account createAtm() {
        Account atm = new Account();
        atm.setId(Values.DEFAULT_ATM_ID);
        atm.setName(Values.DEFAULT_ATM_NAME);
        atm.setBalance(Values.DEFAULT_ATM_BALANCE);

        return atm;
    }

    private double calculateNewBalance(Double oldBalance, Double newBalance) {
        double sum = oldBalance + newBalance;

        if (sum >= 0) {
            return sum;
        }
        throw new NegativeBalanceException(GenericMessage.INSUFFICIENT_BALANCE);
    }

    @Override
    public List<Account> getAll() {
        return _accountRepository.findAll();
    }

    @Override
    public Account findById(long accountId) {
        Optional<Account> accountInDB = this._accountRepository.findById(accountId);

        if (accountInDB.isPresent()) {
            return accountInDB.get();
        }

        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }

    @Override
    public void delete(long accountId) {
        Optional<Account> accountInDB = this._accountRepository.findById(accountId);

        if (accountInDB.isPresent()) {
            _accountRepository.delete(accountInDB.get());
        }
    }

    @Override
    public List<Account> transfer(Account origin, Account destination, double amount) {
        Optional<Account> originAccountInDB = this._accountRepository.findById(origin.getId());
        Optional<Account> destinationAccountInDB = this._accountRepository.findById(destination.getId());
        //List<Account> destinationAccountInDB = this._accountRepository.findAllById(Stream.of(originId, destinationId).collect(Collectors.toList()));

        if (originAccountInDB.isPresent() && destinationAccountInDB.isPresent()) {
            Account originAccountForUpdate = originAccountInDB.get();
            Account destinationAccountForUpdate = destinationAccountInDB.get();

            double destinationNewBalance = calculateNewBalance(destinationAccountForUpdate.getBalance(), amount * -1.0);
            destinationAccountForUpdate.setBalance(destinationNewBalance);

            double originNewBalance = calculateNewBalance(originAccountForUpdate.getBalance(), amount);
            originAccountForUpdate.setBalance(originNewBalance);

//            return this._accountRepository.saveAll(Arrays.asList(originAccountForUpdate, destinationAccountForUpdate));
            return Arrays.asList(originAccountForUpdate, destinationAccountForUpdate);
        }
        throw new AccountNotFoundException(GenericMessage.ACCOUNT_NOT_FOUND);
    }
}

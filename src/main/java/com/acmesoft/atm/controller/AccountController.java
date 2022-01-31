package com.acmesoft.atm.controller;

import com.acmesoft.atm.constants.GenericMessage;
import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.dto.TransferRequest;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService _accountService;

    @GetMapping()
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(_accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable long id) {
        return ResponseEntity.ok(_accountService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Account account) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            return ResponseEntity.ok(_accountService.save(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Account account) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            account.setId(id);
            return ResponseEntity.ok(_accountService.update(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> deposit(@PathVariable long id, @RequestBody Account account) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            account.setId(id);
            return ResponseEntity.ok(_accountService.deposit(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @PatchMapping()
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {

        List<Account> accounts = transferRequest.getAccounts();
        double amount = transferRequest.getAmount();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Account originAccount = accounts.get(0), destinationAccount = accounts.get(1);
        Set<ConstraintViolation<Account>> validations = validator.validate(originAccount);
        validations.addAll(validator.validate(destinationAccount));

        if (validations.isEmpty() && amount > 0) {
            return ResponseEntity.ok(_accountService.transfer(originAccount, destinationAccount, amount));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id) {
        this._accountService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}

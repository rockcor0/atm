package com.acmesoft.atm.controller;

import com.acmesoft.atm.constants.GenericMessage;
import com.acmesoft.atm.dto.AccountRequest;
import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.services.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {

    @Autowired
    private AccountService _accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.ok(_accountService.getAll());
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id){
        return ResponseEntity.ok(_accountService.findById(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> save(@RequestBody Account account){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            return ResponseEntity.ok(_accountService.save(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Account account){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            account.setId(id);
            return ResponseEntity.ok(_accountService.update(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }

    @PutMapping("/accounts/{id}/deposit")
    public ResponseEntity<?> deposit(@PathVariable long id, @RequestBody Account account) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Account>> violations = validator.validate(account);

        if (violations.isEmpty()) {
            account.setId(id);
            return ResponseEntity.ok(_accountService.deposit(account));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }
}

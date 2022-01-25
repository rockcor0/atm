package com.acmesoft.atm.controller;

import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {

    @Autowired
    AccountService _accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Account account){

        return account == null ? null : new ResponseEntity<>(_accountService.deposit(account.getId()), HttpStatus.OK);
    }
}

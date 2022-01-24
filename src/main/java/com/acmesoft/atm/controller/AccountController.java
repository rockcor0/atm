package com.acmesoft.atm.controller;

import com.acmesoft.atm.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {


    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Account account){

        return null;
    }
}

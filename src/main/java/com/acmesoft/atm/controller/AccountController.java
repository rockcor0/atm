package com.acmesoft.atm.controller;

import com.acmesoft.atm.constants.GenericMessage;
import com.acmesoft.atm.dto.GenericRequest;
import com.acmesoft.atm.dto.GenericResponse;
import com.acmesoft.atm.model.Account;
import com.acmesoft.atm.services.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {

    @Autowired
    private AccountService _accountService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody GenericRequest request) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<GenericRequest>> violations = validator.validate(request);

        if (violations.isEmpty()) {
            return ResponseEntity.ok(_accountService.deposit(request.getId()));
        }

        return ResponseEntity.badRequest().body(new GenericResponse(false, GenericMessage.ERROR_MESSAGE));
    }
}

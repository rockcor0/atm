package com.acmesoft.atm.controller;

import com.acmesoft.atm.model.Operation;
import com.acmesoft.atm.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reports")
public class OperationController {

    @Autowired
    OperationService _operationService;

    @GetMapping()
    public ResponseEntity<List<Operation>> getAll(){
        return ResponseEntity.ok(_operationService.findAll());
    }
}

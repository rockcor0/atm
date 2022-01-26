package com.acmesoft.atm.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Account implements Serializable {

    private Long id;

    private String name;

    private Double balance;

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

package com.acmesoft.atm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AccountRequest {

    @NotNull
    private Long id;

    private String name;

    private String balance;
}

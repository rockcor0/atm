package com.acmesoft.atm.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;
import lombok.Setter;

@Setter
@Getter
public class AccountRequest {

    @NotNull
    private Long id;

    private String name;

    private String balance;
}

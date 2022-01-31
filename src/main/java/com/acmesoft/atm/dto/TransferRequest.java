package com.acmesoft.atm.dto;

import com.acmesoft.atm.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TransferRequest implements Serializable {

    @NotNull
    @JsonProperty("accounts")
    private List<Account> accounts;

    @NotNull
    @JsonProperty("amount")
    private double amount;

    public TransferRequest(List<Account> accounts, double amount) {
        this.accounts = accounts;
        this.amount = amount;
    }

    public TransferRequest() {
    }
}

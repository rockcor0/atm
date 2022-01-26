package com.acmesoft.atm.dto;

import lombok.Getter;
import javax.validation.constraints.NotNull;
import lombok.Setter;

@Setter
@Getter
public class GenericRequest {

    @NotNull
    private Long id;

    private String name;

    public GenericRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

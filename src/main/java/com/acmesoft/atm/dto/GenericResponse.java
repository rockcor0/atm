package com.acmesoft.atm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class GenericResponse implements Serializable {

    private boolean success;

    private String error;

    public GenericResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}

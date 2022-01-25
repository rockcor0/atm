package com.acmesoft.atm.dto;

public class GenericResponse {

    public GenericResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    private boolean success;
    private String error;
}

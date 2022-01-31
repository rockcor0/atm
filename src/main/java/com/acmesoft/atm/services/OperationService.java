package com.acmesoft.atm.services;

import com.acmesoft.atm.model.Operation;

import java.util.List;

public interface OperationService<T> {
    T save(T operation);

    List<T> findAllById(Iterable<Long> ids);

    List<T> findAll();
}

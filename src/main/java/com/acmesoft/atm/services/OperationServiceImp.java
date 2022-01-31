package com.acmesoft.atm.services;

import com.acmesoft.atm.model.Operation;
import com.acmesoft.atm.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("operationService")
@Transactional
public class OperationServiceImp implements OperationService<Operation> {

    @Autowired
    private OperationRepository _operationRepository;

    @Override
    public Operation save(Operation operation) {
        return this._operationRepository.save(operation);
    }

    @Override
    public List<Operation> findAllById(Iterable<Long> ids) {
        return this._operationRepository.findAllById(ids);
    }

    @Override
    public List<Operation> findAll() {
        return this._operationRepository.findAll();
    }

}

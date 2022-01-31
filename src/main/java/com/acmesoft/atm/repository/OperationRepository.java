package com.acmesoft.atm.repository;

import com.acmesoft.atm.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}

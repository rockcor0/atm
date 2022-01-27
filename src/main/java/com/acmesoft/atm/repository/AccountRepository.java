package com.acmesoft.atm.repository;

import com.acmesoft.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

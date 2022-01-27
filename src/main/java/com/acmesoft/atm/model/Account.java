package com.acmesoft.atm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "db_account")
public class Account implements Serializable {

    @NotNull
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private Double balance;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

}

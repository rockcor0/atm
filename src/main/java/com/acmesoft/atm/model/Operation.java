package com.acmesoft.atm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "db_operation")
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @NotNull
    private long id;

    @NotNull
    @Column(name = "amount")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction")
    private OperationDirection direction;

//    @ManyToOne()
//    @JoinColumn(name = "account_id")
    private long accountId;

    private long originOrDestinAccountId;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

}

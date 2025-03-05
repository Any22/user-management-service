package com.fin_app.user_service.entity;

import com.fin_app.user_service.dto.AccountHolderName;
import com.fin_app.user_service.dto.Enums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
//Marking a field with the @Transient annotation in an entity class tells Jakarta Persistence
// (or JPA) that this field should not be persisted in the database. In other words, the field
// is ignored during both read and write operations to the database.
@Entity
@Table(name = "user_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name="customer_id" , nullable=false)
    private String customerId;

    @Column(name="customer_name" , nullable=false)
    private AccountHolderName accountHolderName;

    @Embedded
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type", nullable=false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Enums.AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name= "branch_code", nullable=false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Enums.BranchCode branchCode;

    @Column(name="interest_rates" , nullable=false)
    private Double interestRate;

}

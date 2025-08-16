package com.fin_app.user_service.entity;

import com.fin_app.user_service.dto.Enums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;



////Marking a field with the @Transient annotation in an entity class tells Jakarta Persistence
//// (or JPA) that this field should not be persisted in the database. In other words, the field
//// is ignored during both read and write operations to the database.
/// unique= true : This instructs JPA/Hibernate to generate a unique constraint at the database schema level. It ensures that no two rows can hold the same contact number
/// The JPA annotation is essential for persistence logic.
/// If the schema doesn't reflect the constraint, duplicates can slip in.So implement it table query as well if table already exist
@Entity
@Data
@Table(name = "user_data")
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_sequence", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Long customerId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Embedded
    private AccountHolderNameEntity accountHolderName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column (name= "contact_number", unique = true, nullable = false)
    private Long contactNumber;

    @Embedded
    private AddressEntity address;

    @Column(name="account_type", nullable=false)
    private String accountType;

    @Enumerated(EnumType.STRING)
    @Column(name= "branch_code", nullable=false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Enums.BranchCode branchCode;

    @Column(name="interest_rates" , nullable=false)
    private Double interestRate;

}


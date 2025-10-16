package com.fin_app.user_service.entity;

import com.fin_app.user_service.dto.Enums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;


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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Embedded
    private AccountHolderNameEntity accountHolderName;

    @Column (name= "contact_number", unique = true, nullable = false)
    private Long contactNumber;

    @Embedded
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type", nullable=false)
    private Enums.AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name= "branch_code", nullable=false)
    private Enums.BranchCode branchCode ;

    @Enumerated(EnumType.STRING)
    @Column(name= "user_type", nullable=false)
    private Enums.UserType userType ;

    @Column(name="interest_rates" , nullable=false)
    private Double interestRate;

}


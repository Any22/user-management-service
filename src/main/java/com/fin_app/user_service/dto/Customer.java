package com.fin_app.user_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String userId;
    private AccountHolderName accountHolderName;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String emailAddress;
    private Long contactNumber;
    private Address address;
    private String accountType;
    private String userType;
    private String branchCode;
    private Double interestRate;


 }



package com.fin_app.user_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long customerId;
    private String userName;
    private String password;
    private AccountHolderName accountHolderName;
    private String emailAddress;
    private Long contactNumber;
    private Address address;
    private String accountType;
    private String branchCode;
    private Double interestRate;

 }



package com.fin_app.user_service.mapper;

import com.fin_app.user_service.dto.*;
import com.fin_app.user_service.entity.AccountHolderNameEntity;
import com.fin_app.user_service.entity.AddressEntity;
import com.fin_app.user_service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {
    public Customer mapToCustomerDTO(CustomerRequest customerRequest) {
      return   Customer.builder()
              .userName(customerRequest.getUserName())
              .password(customerRequest.getUserPassword())
              .accountHolderName(this.getAccountHolderNameFromRequest(customerRequest))
              .emailAddress(customerRequest.getEmailAddress())
              .contactNumber(customerRequest.getContactNumber())
              .address(this.getAddressFromRequest(customerRequest))
              .accountType(customerRequest.getAccountType())
              .branchCode(String.valueOf(Enums.BranchCode.BR01))
              .interestRate(this.getInterestRate(customerRequest.getAccountType()))
              .build();
    }

    public CustomerEntity convertToEntity(Customer customerDTO) {

        return CustomerEntity.builder()
                .userName(customerDTO.getUserName())
                .password(customerDTO.getPassword())
                .accountHolderName(this.getAccountHolderNameEntity(customerDTO))
                .emailAddress(customerDTO.getEmailAddress())
                .contactNumber(customerDTO.getContactNumber())
                .accountType(customerDTO.getAccountType())
                .address(this.getAddressEntity(customerDTO))
                .branchCode(Enums.BranchCode.valueOf(customerDTO.getBranchCode()))
                .interestRate(customerDTO.getInterestRate())
                .build();
    }

    public AccountHolderNameEntity getAccountHolderNameEntity(Customer customerDTO) {
        return AccountHolderNameEntity
                .builder()
                .firstName(customerDTO.getAccountHolderName().getFirstName())
                .lastName(customerDTO.getAccountHolderName().getLastName())
                .build();

    }
    public AddressEntity getAddressEntity(Customer customerDTO) {
        return AddressEntity
                .builder()
                .addressLine(customerDTO.getAddress().getAddressLine())
                .city(customerDTO.getAddress().getCity())
                .postalCode(customerDTO.getAddress().getPostalCode())
                .state(customerDTO.getAddress().getState())
                .build();
    }

    public AccountHolderName getAccountHolderNameFromRequest (CustomerRequest customerRequest){
        return AccountHolderName.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .build();

    }

    public Address getAddressFromRequest(CustomerRequest customerRequest) {
        return Address.builder()
                .addressLine(customerRequest.getPostalAddress().getAddressLine())
                .city(customerRequest.getPostalAddress().getCity())
                .postalCode(customerRequest.getPostalAddress().getPostalCode())
                .state(customerRequest.getPostalAddress().getState())
                .build();
    }

    public Customer convertToDTO(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerId(customerEntity.getCustomerId())
                .userName(customerEntity.getUserName())
                .password(customerEntity.getPassword())
                .accountHolderName(this.getAccountHolderName(customerEntity))
                .emailAddress(customerEntity.getEmailAddress())
                .contactNumber(customerEntity.getContactNumber())
                .accountType(customerEntity.getAccountType())
                .address(this.getAddress(customerEntity))
                .branchCode(String.valueOf(Enums.BranchCode.valueOf(String.valueOf(customerEntity.getBranchCode()))))
                .interestRate(customerEntity.getInterestRate())
                .build();

    }


    private AccountHolderName getAccountHolderName(CustomerEntity customerEntity) {
        return AccountHolderName
                .builder()
                .firstName(customerEntity.getAccountHolderName().getFirstName())
                .lastName(customerEntity.getAccountHolderName().getLastName())
                .build();

    }

    private Address getAddress(CustomerEntity customerEntity) {
        return Address
                .builder()
                .addressLine(customerEntity.getAddress().getAddressLine())
                .city(customerEntity.getAddress().getCity())
                .postalCode(customerEntity.getAddress().getPostalCode())
                .state(customerEntity.getAddress().getState())
                .build();
    }


    private Double getInterestRate(String accountType) {
        return switch (accountType.toUpperCase()) {
            case  "SAVINGS" -> 2.5;
            case "CURRENT" -> 0.5;
            case "BUSINESS" -> 1.5;
            default -> 0.0; // Default for unknown types
        };
    }

}







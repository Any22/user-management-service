package com.fin_app.user_service.mapper;

import com.fin_app.user_service.dto.*;
import com.fin_app.user_service.entity.AccountHolderNameEntity;
import com.fin_app.user_service.entity.AddressEntity;
import com.fin_app.user_service.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {
//    public Customer mapToCustomerDTO(CustomerRequest customerRequest) {
//      return   Customer.builder()
//              .userName(customerRequest.getUserName())
//              .password(customerRequest.getUserPassword())
//              .accountHolderName(this.getAccountHolderNameFromRequest(customerRequest))
//              .emailAddress(customerRequest.getEmailAddress())
//              .contactNumber(customerRequest.getContactNumber())
//              .address(customerRequest.getPostalAddress())
//              .accountType(customerRequest.getAccountType())
//              .branchCode(String.valueOf(Enums.BranchCode.BR01))
//              .interestRate(this.getInterestRate(customerRequest.getAccountType()))
//              .build();
//    }

    public CustomerEntity convertToEntity(CustomerRequest customerReq) {

        return CustomerEntity.builder()
                .password(customerReq.getUserPassword())
                .accountHolderName(this.getAccountHolderNameEntity(customerReq))
                .emailAddress(customerReq.getEmailAddress())
                .contactNumber(customerReq.getContactNumber())
                .address(this.getAddressEntity(customerReq))
                .accountType(Enums.AccountType.valueOf(customerReq.getAccountType()))
                .address(this.getAddressEntity(customerReq))
                .branchCode(this.getBranchCode(customerReq.getPostalAddress().getState()))
                .userType(Enums.UserType.USER)
                .interestRate(this.getInterestRate(customerReq.getAccountType()))
                .build();
    }

    public AccountHolderNameEntity getAccountHolderNameEntity(CustomerRequest customerRequest) {
        return AccountHolderNameEntity
                .builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .build();

    }
    public AddressEntity getAddressEntity(CustomerRequest customerRequest) {
        return AddressEntity
                .builder()
                .addressLine(customerRequest.getPostalAddress().getAddressLine())
                .city(customerRequest.getPostalAddress().getCity())
                .postalCode(customerRequest.getPostalAddress().getPostalCode())
                .state(customerRequest.getPostalAddress().getState())
                .build();
    }

//    public AccountHolderName getAccountHolderNameFromRequest (CustomerRequest customerRequest){
//        return AccountHolderName.builder()
//                .firstName(customerRequest.getFirstName())
//                .lastName(customerRequest.getLastName())
//                .build();
//
//    }

//    public Address getAddressFromRequest(CustomerRequest customerRequest) {
//        return Address.builder()
//                .addressLine(customerRequest.getPostalAddress().getAddressLine())
//                .city(customerRequest.getPostalAddress().getCity())
//                .postalCode(customerRequest.getPostalAddress().getPostalCode())
//                .state(customerRequest.getPostalAddress().getState())
//                .build();
//    }

    public Customer convertToDTO(CustomerEntity customerEntity) {
        return Customer.builder()
                .userId(customerEntity.getUserId())
                .password(customerEntity.getPassword())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdateAt())
                .accountHolderName(this.getAccountHolderName(customerEntity))
                .emailAddress(customerEntity.getEmailAddress())
                .contactNumber(customerEntity.getContactNumber())
                .address(this.getAddress(customerEntity))
                .accountType(String.valueOf(customerEntity.getAccountType()))
                .userType(String.valueOf(customerEntity.getUserType()))
                .branchCode(customerEntity.getBranchCode().toString())
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

    private  Enums.BranchCode getBranchCode (String state) {
        return switch (state.toUpperCase()) {
            case  "VIC" -> Enums.BranchCode.BR01;
            case "NSW" -> Enums.BranchCode.BR02;
            case "QLD" -> Enums.BranchCode.BR03;
            case "SA" -> Enums.BranchCode.BR04;
            case "WA" -> Enums.BranchCode.BR05;
            case "TAS"-> Enums.BranchCode.BR06;
            default -> Enums.BranchCode.BR01; // Default for unknown types
        };
    }

}







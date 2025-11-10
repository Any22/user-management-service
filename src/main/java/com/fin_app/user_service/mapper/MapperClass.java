package com.fin_app.user_service.mapper;

import com.fin_app.user_service.dto.*;
import com.fin_app.user_service.entity.AccountHolderNameEntity;
import com.fin_app.user_service.entity.AddressEntity;
import com.fin_app.user_service.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {

    public UserEntity convertToEntity(UserRequest userRequest) {

        return UserEntity.builder()
                .password(userRequest.getUserPassword())
                .accountHolderName(this.getAccountHolderNameEntity(userRequest))
                .emailAddress(userRequest.getEmailAddress())
                .contactNumber(userRequest.getContactNumber())
                .address(this.getAddressEntity(userRequest))
                .accountType(Enums.AccountType.valueOf(userRequest.getAccountType()))
                .address(this.getAddressEntity(userRequest))
                .branchCode(this.getBranchCode(userRequest.getPostalAddress().getState()))
                .userType(Enums.UserType.USER)
                .interestRate(this.getInterestRate(userRequest.getAccountType()))
                .build();
    }

    public AccountHolderNameEntity getAccountHolderNameEntity(UserRequest userRequest) {
        return AccountHolderNameEntity
                .builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();

    }
    public AddressEntity getAddressEntity(UserRequest userRequest) {
        return AddressEntity
                .builder()
                .addressLine(userRequest.getPostalAddress().getAddressLine())
                .city(userRequest.getPostalAddress().getCity())
                .postalCode(userRequest.getPostalAddress().getPostalCode())
                .state(userRequest.getPostalAddress().getState())
                .build();
    }


    public User convertToDTO(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdateAt())
                .accountHolderName(this.getAccountHolderName(userEntity))
                .emailAddress(userEntity.getEmailAddress())
                .contactNumber(userEntity.getContactNumber())
                .address(this.getAddress(userEntity))
                .accountType(String.valueOf(userEntity.getAccountType()))
                .userType(String.valueOf(userEntity.getUserType()))
                .branchCode(userEntity.getBranchCode().toString())
                .interestRate(userEntity.getInterestRate())
                .build();

    }


    private AccountHolderName getAccountHolderName(UserEntity userEntity) {
        return AccountHolderName
                .builder()
                .firstName(userEntity.getAccountHolderName().getFirstName())
                .lastName(userEntity.getAccountHolderName().getLastName())
                .build();

    }

    private Address getAddress(UserEntity userEntity) {
        return Address
                .builder()
                .addressLine(userEntity.getAddress().getAddressLine())
                .city(userEntity.getAddress().getCity())
                .postalCode(userEntity.getAddress().getPostalCode())
                .state(userEntity.getAddress().getState())
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







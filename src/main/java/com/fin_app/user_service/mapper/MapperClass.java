package com.fin_app.user_service.mapper;

import com.fin_app.user_service.dto.AccountHolderName;
import com.fin_app.user_service.dto.Address;
import com.fin_app.user_service.dto.Customer;
import com.fin_app.user_service.dto.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {
    public Customer mapToCustomerDTO(@Valid CustomerRequest customerRequest) {
// a seperate method can be made to populate accountHolder name
      return   Customer.builder()
              .accountHolderName(AccountHolderName.builder()
                      .firstName(customerRequest.getFirstName())
                      .lastName(customerRequest.getLastName()).build())
              .address(Address.builder().build())
              .accountType(customerRequest.getAccountType())
              .build();

    }


//    private Double getInterestRate(String accountType) {
//        switch (accountType.toUpperCase()) {
//            case "SAVINGS":
//                return 2.5;
//            case "CHECKING":
//                return 0.5;
//            case "BUSINESS":
//                return 1.5;
//            default:
//                return 0.0; // Default for unknown types
//        }
//    }
}







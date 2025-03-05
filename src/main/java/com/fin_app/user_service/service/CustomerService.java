package com.fin_app.user_service.service;

import com.fin_app.user_service.dto.Customer;
import com.fin_app.user_service.dto.CustomerRequest;
import com.fin_app.user_service.entity.CustomerEntity;
import com.fin_app.user_service.mapper.MapperClass;
import com.fin_app.user_service.respository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final MapperClass mapper;
    private final CustomerRepository customerRepository;

    public Customer registerNewUser(@Valid CustomerRequest customerRequest) {
        Customer newCustomer = mapper.mapToCustomerDTO(customerRequest);

        CustomerEntity customerEntity= this.convertToEntity(newCustomer);
        this.savingToDb(customerEntity);
        return  newCustomer;
    }

    private CustomerEntity convertToEntity(Customer customerDTO) {

        return CustomerEntity.builder()

                .build();
    }

    private void savingToDb(CustomerEntity newCustomer) {
        customerRepository.save(newCustomer);
    }
}

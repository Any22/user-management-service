package com.fin_app.user_service.service;

import com.fin_app.user_service.dto.Customer;
import com.fin_app.user_service.dto.CustomerRequest;
import com.fin_app.user_service.dto.LoginRequest;

import com.fin_app.user_service.entity.CustomerEntity;
import com.fin_app.user_service.exception.DuplicateRecordExist;
import com.fin_app.user_service.exception.NoDataFoundException;
import com.fin_app.user_service.mapper.MapperClass;
import com.fin_app.user_service.respository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final MapperClass mapper;
    private final CustomerRepository customerRepository;

    public Customer registerCustomer(@Valid CustomerRequest customerRequest)  {
        String userName = customerRequest.getUserName();
        Optional<CustomerEntity> optionalCustomerEntity=  customerRepository.findByUserName(userName);

        if (optionalCustomerEntity.isPresent()){
            throw new DuplicateRecordExist("The user "+userName + " already exists");
        }

        Customer newCustomer = mapper.mapToCustomerDTO(customerRequest);
        log.info("The customer after mapping {}", newCustomer);
        CustomerEntity entity = mapper.convertToEntity(newCustomer);
        this.savingToDb(entity);

        return mapper.convertToDTO(entity);


    }


    private void savingToDb(CustomerEntity newCustomer) {
        customerRepository.save(newCustomer);
    }


    public String loginRequest(@Valid LoginRequest loginRequest) throws NoDataFoundException {
        Optional<CustomerEntity> optionalCustomerEntity =  customerRepository.findByUserNameAndPassword(loginRequest.getUserName()
                , loginRequest.getPassword());
        log.info(loginRequest.getUserName() +" " + loginRequest.getPassword());
      if( optionalCustomerEntity.isPresent()){
        //  log.info("The user name and password"+ op)
          return "Your login is successful";
      }
      else {
        throw new NoDataFoundException("The user credentials is wrong");
       }
    }
}

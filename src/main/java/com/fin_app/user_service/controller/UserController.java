package com.fin_app.user_service.controller;


import com.fin_app.user_service.dto.Customer;
import com.fin_app.user_service.dto.CustomerRequest;
import com.fin_app.user_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/api/users")
@Slf4j
@Validated
@RequiredArgsConstructor
public class UserController {
    private  final CustomerService userService;

    @PostMapping(value={"/register"}, consumes= APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> registerNewCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        try{
             Customer response = userService.createNewAccount(customerRequest);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

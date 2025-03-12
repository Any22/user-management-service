package com.fin_app.user_service.controller;


import com.fin_app.user_service.dto.Customer;
import com.fin_app.user_service.dto.CustomerRequest;
import com.fin_app.user_service.dto.LoginRequest;
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
    public ResponseEntity<String> registerNewCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        try{
            log.info("The customerRequest is {}",customerRequest );
             Customer response = userService.registerCustomer(customerRequest);
            return new ResponseEntity<>("The user with id : " + response.getCustomerId()+ " is registered successfully",HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping(value={"/login"}, consumes= APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try{
            log.info("Login Request received is {}", loginRequest );
             String status=  userService.loginRequest(loginRequest);
           return new ResponseEntity<>(status,HttpStatus.FOUND);

        } catch (Exception e) {
            throw new Exception(e);
            //return new ResponseEntity<> (ErrorMessage.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.getMessage()).build());
        }

    }
}

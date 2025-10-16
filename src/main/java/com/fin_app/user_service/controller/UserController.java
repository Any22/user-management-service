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
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//https://www.youtube.com/watch?v=_FdKTSFnWeg&list=PLxhSr_SLdXGO6LQ-yZpwvO6a8IP7mDkMp
@RestController
@RequestMapping("v1/api/users")
@Slf4j
@Validated
@RequiredArgsConstructor
public class UserController {
    private  final CustomerService userService;


    @PostMapping(value={"/register"}, consumes= APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerNewCustomer(@Valid @RequestBody CustomerRequest customerRequest) throws Exception {
        try{
            log.info("The customerRequest is {}",customerRequest );
            Customer response = userService.registerCustomer(customerRequest);
            return new ResponseEntity<>("The user registered successfully with id : " + response.getUserId(),HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }


    }

    @PostMapping(value={"/login"}, consumes= APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try{
            log.info("Login Request received is {}", loginRequest );
             String status =  userService.loginRequest(loginRequest);
           return new ResponseEntity<>(status,HttpStatus.CREATED);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping(value={"/fetch/{customerId}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchCustomerById(@PathVariable("customerId") String userId) throws Exception {
        try{
            log.info("The customerId is  {}", userId);
            Customer customer =  userService.fetchCustomerById(userId);
            return new ResponseEntity<>(customer,HttpStatus.OK);

        } catch (Exception e) {
            throw new Exception(e);
        }

    }


}

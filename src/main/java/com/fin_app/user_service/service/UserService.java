package com.fin_app.user_service.service;

import com.fin_app.user_service.dto.User;
import com.fin_app.user_service.dto.UserRequest;
import com.fin_app.user_service.dto.LoginRequest;

import com.fin_app.user_service.entity.UserEntity;
import com.fin_app.user_service.exception.DuplicateRecordExist;
import com.fin_app.user_service.exception.NoDataFoundException;
import com.fin_app.user_service.exception.UserServiceConstants;
import com.fin_app.user_service.mapper.MapperClass;
import com.fin_app.user_service.respository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final MapperClass mapper;
    private final UserRepository customerRepository;

    public User registerCustomer(@Valid UserRequest customerRequest)  {
        String emailAddress = customerRequest.getEmailAddress();
        Optional<UserEntity> optionalCustomerEntity=  customerRepository.findByEmailAddress(emailAddress);

        if (optionalCustomerEntity.isPresent()){
            throw new DuplicateRecordExist(UserServiceConstants.USER_WITH_EMAIL_ALREADY_EXISTS +": "+emailAddress);
        }

        UserEntity newCustomerEntity = mapper.convertToEntity(customerRequest);

        this.savingToDb(newCustomerEntity);
        log.info("The new customer after mapping {}", newCustomerEntity);

        return mapper.convertToDTO(newCustomerEntity);
    }


    private void savingToDb(UserEntity newCustomer) {
     try {
         customerRepository.save(newCustomer);
     } catch(DataIntegrityViolationException ex){
         throw new DuplicateRecordExist("The data should should be unique like email or contact number");
     }
    }


    public String loginRequest(@Valid LoginRequest loginRequest) throws NoDataFoundException {
        Optional<UserEntity> optionalCustomerEntity =  customerRepository.findByUserIdAndPassword(loginRequest.getUserId()
                , loginRequest.getPassword());

      if( optionalCustomerEntity.isPresent()){
          return UserServiceConstants.SUCCESSFUL_LOGIN_MESSAGE.toString();
      }
      else {
        throw new NoDataFoundException(UserServiceConstants.INVALID_CREDENTIALS_MESSAGE.toString());
       }
    }

    public User fetchCustomerById (String userId) throws NoDataFoundException {
        Optional<UserEntity> optionalCustomerEntity=  customerRepository.findById(userId);
        if (optionalCustomerEntity.isPresent()){
            UserEntity entity = optionalCustomerEntity.get();
            return mapper.convertToDTO(entity);
        }else {
            throw new NoDataFoundException(UserServiceConstants.ID_DOES_NOT_EXIST.toString());
        }

    }

    public Boolean existByUserId(String userId) {
        log.info("Calling user validation API for userId:{}",userId);
        return  customerRepository.existsById(userId);

    }
}

package com.fin_app.user_service.respository;

import com.fin_app.user_service.entity.CustomerEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByEmailAddress(String emailAddress);
    Optional<CustomerEntity> findByUserIdAndPassword(String userId, String password);
}

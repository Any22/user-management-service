package com.fin_app.user_service.respository;

import com.fin_app.user_service.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUserName(String userName);
    Optional<CustomerEntity> findByUserNameAndPassword(String userName, String password);
}

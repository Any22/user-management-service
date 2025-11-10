package com.fin_app.user_service.respository;

import com.fin_app.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmailAddress(String emailAddress);
    Optional<UserEntity> findByUserIdAndPassword(String userId, String password);

}

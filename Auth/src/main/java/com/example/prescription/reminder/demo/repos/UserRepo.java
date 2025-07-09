package com.example.prescription.reminder.demo.repos;

import com.example.prescription.reminder.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

    Optional <UserEntity> findByUsername(String username);
}

package com.cakefactory.repository;

import com.cakefactory.model.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Long> {
    Optional<UserCredentials> findByEmail(String email);
}

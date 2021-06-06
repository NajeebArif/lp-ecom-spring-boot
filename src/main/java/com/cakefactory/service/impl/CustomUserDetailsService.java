package com.cakefactory.service.impl;

import com.cakefactory.model.entity.UserCredentials;
import com.cakefactory.model.security.CustomUserDetails;
import com.cakefactory.repository.UserCredentialsRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialsRepo userCredentialsRepo;

    public CustomUserDetailsService(UserCredentialsRepo userCredentialsRepo) {
        this.userCredentialsRepo = userCredentialsRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialsRepo.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User Does not exists"));
    }
}

package com.cakefactory.service.impl;

import com.cakefactory.model.security.CustomUserDetails;
import com.cakefactory.repository.UserCredentialsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialsRepo userCredentialsRepo;

    public CustomUserDetailsService(UserCredentialsRepo userCredentialsRepo) {
        this.userCredentialsRepo = userCredentialsRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CustomUserDetails userDetails = userCredentialsRepo.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User Does not exists"));
        return userDetails;
    }
}

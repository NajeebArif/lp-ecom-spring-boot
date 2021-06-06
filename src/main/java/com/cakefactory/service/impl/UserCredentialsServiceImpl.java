package com.cakefactory.service.impl;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.model.entity.User;
import com.cakefactory.model.entity.UserCredentials;
import com.cakefactory.repository.UserCredentialsRepo;
import com.cakefactory.service.UserCredentialsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private final PasswordEncoder passwordEncoder;
    private final UserCredentialsRepo userCredentialsRepo;

    public UserCredentialsServiceImpl(PasswordEncoder passwordEncoder, UserCredentialsRepo userCredentialsRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userCredentialsRepo = userCredentialsRepo;
    }

    @Override
    public void saveUserCredentials(User user, String password) {
        final UserCredentials userCredentials = mapToUserCred(user, password);
        userCredentialsRepo.save(userCredentials);
    }

    private UserCredentials mapToUserCred(User user, String password) {
        final UserCredentials userCredentials = new UserCredentials();
        userCredentials.setEmail(user.getEmail());
        userCredentials.setPassword(passwordEncoder.encode(password));
        userCredentials.setEnabled(true);
        userCredentials.setUser(user);
        return userCredentials;
    }
}

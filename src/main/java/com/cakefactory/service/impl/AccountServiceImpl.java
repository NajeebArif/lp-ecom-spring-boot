package com.cakefactory.service.impl;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.model.entity.Address;
import com.cakefactory.model.entity.User;
import com.cakefactory.model.entity.UserAuthority;
import com.cakefactory.repository.UserRepo;
import com.cakefactory.service.AccountService;
import com.cakefactory.service.UserCredentialsService;
import com.cakefactory.utils.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import static com.cakefactory.utils.Mappers.*;
import static com.cakefactory.utils.SecurityHelper.setSecurityContext;

@Service
@Slf4j
@SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
public class AccountServiceImpl implements AccountService {

    private UserDto loggedInUser;

    private final UserRepo userRepo;
    private final UserCredentialsService userCredentialsService;

    public AccountServiceImpl(UserRepo userRepo, UserCredentialsService userCredentialsService) {
        this.userRepo = userRepo;
        this.userCredentialsService = userCredentialsService;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = getUser(userDto);
        final User savedUser = userRepo.save(user);
        userCredentialsService.saveUserCredentials(savedUser,userDto.getPassword());
        loggedInUser = mapUserToUserDto(savedUser);
        setSecurityContext(user);
        return loggedInUser;
    }

    @Override
    public UserDto getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    @Deprecated
    public UserDto logIn(String email, String password) {
        loggedInUser = userRepo.findByEmail(email).map(Mappers::mapUserToUserDto).orElseThrow(() -> new RuntimeException("Invalid Credentials"));
        return loggedInUser;
    }

    @Override
    public void logout() {
        loggedInUser = null;
    }

    private User getUser(UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        Address address = mapUserDtoToAddress(userDto);
        user.addAddress(address);
        final UserAuthority userAuthority = getClientAuthority();
        user.addAuthority(userAuthority);
        return user;
    }

    private UserAuthority getClientAuthority() {
        final UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority("client");
        return userAuthority;
    }



}

package com.cakefactory.service.impl;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.model.entity.Address;
import com.cakefactory.model.entity.User;
import com.cakefactory.repository.UserRepo;
import com.cakefactory.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
@SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
public class AccountServiceImpl implements AccountService {

    private UserDto loggedInUser;

    private final UserRepo userRepo;

    public AccountServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = mapToUser(userDto);
        Address address = mapToAddress(userDto);
        user.addAddress(address);
        final User savedUser = userRepo.save(user);
        loggedInUser = mapToUserDto(savedUser);
        return loggedInUser;
    }

    @Override
    public UserDto getLoggedInUser() {
        return loggedInUser;
    }

    private UserDto mapToUserDto(User savedUser) {
        return UserDto.builder()
                .userName(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }

    private Address mapToAddress(UserDto userDto) {
        Address address = new Address();
        address.setAddressLine1(userDto.getAddressLine1());
        address.setAddressLine2(userDto.getAddressLine2());
        address.setCity(userDto.getCity());
        address.setPostcode(userDto.getPostCode());
        return address;
    }

    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setAge(userDto.getAge());
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}

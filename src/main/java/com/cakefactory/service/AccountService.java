package com.cakefactory.service;

import com.cakefactory.model.dto.UserDto;

public interface AccountService {

    UserDto registerUser(UserDto userDto);

    UserDto getLoggedInUser();

    UserDto logIn(String email, String password);

    void logout();

    UserDto fetchUserForEmail(String email);
}

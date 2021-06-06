package com.cakefactory.service;

import com.cakefactory.model.dto.UserDto;

public interface AccountService {

    UserDto registerUser(UserDto userDto);

    UserDto getLoggedInUser();
}

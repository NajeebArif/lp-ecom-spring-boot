package com.cakefactory.utils;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.model.entity.Address;
import com.cakefactory.model.entity.User;
import com.cakefactory.model.entity.UserCredentials;
import com.cakefactory.model.security.CustomUserDetails;

public class Mappers {

    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setAge(userDto.getAge());
        user.setUsername(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }


    public static UserDto mapUserToUserDto(User savedUser) {
        return UserDto.builder()
                .userName(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();
    }

    public static Address mapUserDtoToAddress(UserDto userDto) {
        Address address = new Address();
        address.setAddressLine1(userDto.getAddressLine1());
        address.setAddressLine2(userDto.getAddressLine2());
        address.setCity(userDto.getCity());
        address.setPostcode(userDto.getPostCode());
        return address;
    }

    public static CustomUserDetails mapUserToCustomUserDetails(User user){
        final UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUser(user);
        userCredentials.setEnabled(true);
        userCredentials.setEmail(user.getEmail());
        final CustomUserDetails customUserDetails = new CustomUserDetails(userCredentials);
        return customUserDetails;
    }

    public static User mapUserDetailsToUser(CustomUserDetails customUserDetails){
        final User user = new User();
        user.setEmail(customUserDetails.getUsername());
        return user;
    }
}

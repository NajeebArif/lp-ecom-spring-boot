package com.cakefactory.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;


}

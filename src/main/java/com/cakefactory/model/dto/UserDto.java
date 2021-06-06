package com.cakefactory.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private Integer age;
    private String email;
    private String password;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;


}

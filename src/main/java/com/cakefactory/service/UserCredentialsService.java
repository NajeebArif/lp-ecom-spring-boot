package com.cakefactory.service;

import com.cakefactory.model.entity.User;

public interface UserCredentialsService {

    void saveUserCredentials(User user, String password);
}

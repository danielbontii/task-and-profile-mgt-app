package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.LoginInput;

public interface AuthService {
    String authenticateUsernameAndPassword(LoginInput loginInput);
}

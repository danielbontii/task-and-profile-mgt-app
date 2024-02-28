package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.LoginInput;

public interface AuthService {
    /**
     * Returns a token for a successfully authenticated user
     *
     * @param loginInput the login details of the user
     * @return a token
     */
    String authenticateUsernameAndPassword(LoginInput loginInput);
}

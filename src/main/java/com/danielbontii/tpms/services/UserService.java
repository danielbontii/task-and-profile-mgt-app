package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponse;

public interface UserService {
    UserResponse save(UserCreationInput userCreationInput);
}

package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponse save(UserCreationInput userCreationInput);
}

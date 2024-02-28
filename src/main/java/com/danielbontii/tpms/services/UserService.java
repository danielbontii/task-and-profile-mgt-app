package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * This creates a new user in the system
     *
     * @param userCreationInput the user details
     * @return UserResponse the details of the newly created user
     */
    UserResponse save(UserCreationInput userCreationInput);
}

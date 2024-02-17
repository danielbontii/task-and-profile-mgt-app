package com.danielbontii.tpms.services;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO save(UserCreationInput userCreationInput);
}

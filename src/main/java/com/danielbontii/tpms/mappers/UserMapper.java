package com.danielbontii.tpms.mappers;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponse;
import com.danielbontii.tpms.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserMapper(ObjectMapper objectMapper, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.objectMapper = objectMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User toUser(UserCreationInput userCreationInput) {
        User user = new User();
        user.setFirstName(userCreationInput.getFirstName().trim());
        user.setLastName(userCreationInput.getLastName().trim());
        user.setEmail(userCreationInput.getEmail().trim().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(userCreationInput.getPassword()));
        return user;
    }

    public UserResponse toUserResponseDTO(User user) {
        return objectMapper.convertValue(user, UserResponse.class);
    }
}

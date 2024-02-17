package com.danielbontii.tpms.mappers;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponseDTO;
import com.danielbontii.tpms.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User toUser(UserCreationInput userCreationInput) {
        User user = new User();
        user.setFirstName(userCreationInput.getFirstName().trim());
        user.setLastName(userCreationInput.getLastName().trim());
        user.setEmail(userCreationInput.getEmail().trim().toLowerCase());
        user.setPassword(bCryptPasswordEncoder.encode(userCreationInput.getPassword()));
        return user;
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        return objectMapper.convertValue(user, UserResponseDTO.class);
    }
}

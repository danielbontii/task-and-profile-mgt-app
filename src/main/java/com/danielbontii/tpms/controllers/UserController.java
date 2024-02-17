package com.danielbontii.tpms.controllers;

import com.danielbontii.tpms.dtos.UserCreationInput;
import com.danielbontii.tpms.dtos.response.UserResponseDTO;
import com.danielbontii.tpms.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MutationMapping
    public UserResponseDTO createUser(@Argument(name = "info") @Valid UserCreationInput userCreationInput) {
        return userService.save(userCreationInput);
    }
}

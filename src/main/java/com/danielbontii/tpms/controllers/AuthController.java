package com.danielbontii.tpms.controllers;

import com.danielbontii.tpms.dtos.LoginInput;
import com.danielbontii.tpms.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @QueryMapping
    public String usernamePasswordToken(@Argument(name = "loginInput") LoginInput loginInput) {
        return authService.authenticateUsernameAndPassword(loginInput);
    }

}

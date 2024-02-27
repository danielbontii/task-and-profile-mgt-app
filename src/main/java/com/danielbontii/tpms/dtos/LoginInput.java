package com.danielbontii.tpms.dtos;

import lombok.Data;

@Data
public class LoginInput {
    private String email;
    private String password;
}

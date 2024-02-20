package com.danielbontii.tpms.dtos.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}

package com.danielbontii.tpms.dtos.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}

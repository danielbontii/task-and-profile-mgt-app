package com.danielbontii.tpms.dtos;

import lombok.Data;

@Data
public class TodoRequestDTO {

    //Todo: Handle validations

    private String title;
    private String description;
    private Long userId;
    private boolean completed = Boolean.FALSE;
}

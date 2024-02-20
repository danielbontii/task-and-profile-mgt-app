package com.danielbontii.tpms.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TodoCreationInput {

    @Length(min = 1, max = 255, message = "Title must be 1 to 255 characters long")
    private String title;

    @Length(min = 1, max = 255, message = "Description must be 1 to 255 characters long")
    private String description;
    private Long userId;
    private boolean completed = Boolean.FALSE;
}

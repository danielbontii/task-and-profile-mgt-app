package com.danielbontii.tpms.dtos;

import lombok.Data;

@Data
public class TodoUpdateRequestDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}

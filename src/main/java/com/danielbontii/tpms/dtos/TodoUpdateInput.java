package com.danielbontii.tpms.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TodoUpdateInput extends TodoCreationInput {
    private Long id;
}

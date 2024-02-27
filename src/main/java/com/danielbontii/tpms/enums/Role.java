package com.danielbontii.tpms.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"), USER("USER");

    private final String code;

}

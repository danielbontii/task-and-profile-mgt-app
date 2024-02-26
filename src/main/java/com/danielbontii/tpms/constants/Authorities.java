package com.danielbontii.tpms.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Authorities {

    public static final String ADMIN = "SCOPE_ADMIN";
    public static final String USER = "SCOPE_USER";
}

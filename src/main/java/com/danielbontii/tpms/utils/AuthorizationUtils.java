package com.danielbontii.tpms.utils;

import com.danielbontii.tpms.models.Todo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationUtils {

    public static boolean permits(Authentication authentication, String authority) {
        List<String> scopes = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return scopes.contains(authority);
    }

    public static Jwt getPrincipal(Authentication authentication) {
        return (Jwt) authentication.getPrincipal();
    }

    public static boolean authorizesTodoManipulation(Authentication authentication, Todo todo) {
        return todo.getUser().getEmail().equals(getPrincipal(authentication).getClaimAsString("sub"));
    }
}

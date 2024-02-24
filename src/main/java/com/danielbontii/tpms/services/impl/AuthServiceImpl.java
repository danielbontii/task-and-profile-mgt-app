package com.danielbontii.tpms.services.impl;

import com.danielbontii.tpms.dtos.LoginInput;
import com.danielbontii.tpms.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticateUsernameAndPassword(LoginInput loginInput) {

        String username = loginInput.getEmail();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, loginInput.getPassword()));

        if (authentication.isAuthenticated()) {
            Instant now = Instant.now();

            //TODO: Add Scope
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.HOURS))
                    .subject(authentication.getName())
                    .build();
            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        }

        throw new UsernameNotFoundException("Invalid credentials");
    }
}

package com.bluementors.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfiguration {

    @Value("${app.jwtSecret}")
    public String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    public int jwtExpirationInMs;

    @Value("${app.jwtTokenName}")
    public String jwtTokenName;

    @Value("${app.AppAuthSchema}")
    public String jwtAuthSchema;

}

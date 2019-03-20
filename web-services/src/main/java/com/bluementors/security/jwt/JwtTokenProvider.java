package com.bluementors.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String AUTHORITIES_KEY = "authorities";
    private static final String SPRING_ROLE_PREFIX = "ROLE_";

    @Autowired
    private JwtConfiguration jwtConfiguration;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfiguration.jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.jwtSecret.getBytes())
                .claim(AUTHORITIES_KEY, authentication.
                        getAuthorities()
                        .stream()
                        .map(s -> s.getAuthority())
                        .collect(Collectors.toList()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken springAuthToken(String jwtToken) {
        // 4. Validate the token
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfiguration.jwtSecret.getBytes())
                .parseClaimsJws(jwtToken)
                .getBody();

        String username = claims.getSubject();

        if (isNull(username)) {
            throw new JwtHandleException();
        }

        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get(AUTHORITIES_KEY);

        // 4.2 Create auth object
        // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
        // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities.stream()
                        .map(authority -> SPRING_ROLE_PREFIX.concat(authority))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

    }

}

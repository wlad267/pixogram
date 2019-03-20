package com.bluementors.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

@Component
public class JwtRequestExtractor {

    private static final String NULL_TOKEN = null;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    public String readToken(HttpServletRequest httpServletRequest) {
        // 1. check the token cookie
        String token = this.readCookieToken(httpServletRequest);

        // 2. if no cookie than maybe header
        if (!isNull(token)) {
            return token;

        }

        return this.readHeaderToken(httpServletRequest);
    }

    private String readCookieToken(HttpServletRequest httpServletRequest) {
        if (isNull(httpServletRequest.getCookies()) || httpServletRequest.getCookies().length == 0) {
            return NULL_TOKEN;
        }

        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (jwtConfiguration.jwtTokenName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return NULL_TOKEN;
    }

    private String readHeaderToken(HttpServletRequest httpServletRequest) {
        // 3.1 if not cookie try to get the authentication header as supposed to be passed in the authentication header
        String header = httpServletRequest.getHeader(jwtConfiguration.jwtTokenName);
        // 3.2 validate the header and check the prefix
        if (header == null || !header.startsWith(jwtConfiguration.jwtAuthSchema)) {

            // If there is no token provided and hence the user won't be authenticated.
            // It's Ok. Maybe the user accessing a public path or asking for a token.
            return NULL_TOKEN;
        }

        return header.replace(jwtConfiguration.jwtAuthSchema, "");
    }
}

package com.bluementors.security.jwt;

import com.bluementors.security.WebSecurityUserDetails;
import com.bluementors.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationFilterTest {

    @InjectMocks
    JwtAuthenticationFilter sut = new JwtAuthenticationFilter(new String[]{});

    @Spy
    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private JwtConfiguration jwtConfiguration;

    @Spy
    @InjectMocks
    private JwtRequestExtractor jwtRequestExtractor;

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;


    @Before
    public void setup() {
        jwtConfiguration.jwtTokenName = "jwt_";
        jwtConfiguration.jwtExpirationInMs = 1000000;
        jwtConfiguration.jwtSecret = "the_secret_of_the_fire";

        // mocking 2th layer 'injection' limitation
        ReflectionTestUtils.setField(sut, "tokenProvider", jwtTokenProvider);
        ReflectionTestUtils.setField(sut, "jwtRequestExtractor", jwtRequestExtractor);
    }

    @Test
    public void no_cookie_no_header_jwt() throws ServletException, IOException {
        sut.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    public void cookie_jwt() throws ServletException, IOException {
        String token = getJwtTocken();
        when(httpServletRequest.getCookies())
                .thenReturn(new Cookie[]{new Cookie(jwtConfiguration.jwtTokenName, token)});

        sut.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);

        // spring is amazing
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assertThat(authentication)
                .isNotNull()
                .extracting(Authentication::getName)
                .isEqualTo("admin@bluementors.com");
    }


    private String getJwtTocken() {
        return jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(securityUser(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("user"))));
    }

    private UserDetails securityUser() {
        return WebSecurityUserDetails
                .create(new User.Builder()
                        .email("admin@bluementors.com")
                        .authenticationString("password")
                        .build()
                );
    }

}

package com.bluementors.security.jwt;

import com.bluementors.security.WebSecurityUserDetails;
import com.bluementors.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenProviderTest {

    Logger log = LoggerFactory.getLogger(JwtTokenProviderTest.class);

    @InjectMocks
    private JwtTokenProvider sut;

    @Mock
    private JwtConfiguration jwtConfiguration;

    @Before
    public void setup() {
        jwtConfiguration.jwtTokenName = "X_JWT";
        jwtConfiguration.jwtExpirationInMs = 1000000002;
        jwtConfiguration.jwtSecret = "the_secret";

    }

    @Test
    public void generate_token() {
        String jwtToken = sut.generateToken(
                new UsernamePasswordAuthenticationToken(securityUser(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("user"))));

        log.info("\n\n\ngeneratedToken {}\n\n\n", jwtToken);
        assertThat(jwtToken)
                .isNotNull()
                .isNotBlank();
    }

    @Test
    public void parse_token() {
        String jwtToken = sut.generateToken(
                new UsernamePasswordAuthenticationToken(securityUser(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("user"))));

        UsernamePasswordAuthenticationToken user = sut.springAuthToken(jwtToken);

        assertThat(user)
                .isNotNull()
                .extracting(UsernamePasswordAuthenticationToken::getName)
                .isEqualTo("admin@bluementors.com");

        assertThat(user)
                .isNotNull()
                .extracting(UsernamePasswordAuthenticationToken::getAuthorities)
                .isEqualTo(Collections.singletonList(new SimpleGrantedAuthority("ROLE_user")));

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

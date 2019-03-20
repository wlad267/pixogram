package com.bluementors;


import com.bluementors.security.AARequest;
import com.bluementors.security.aa.AAResource;
import com.bluementors.security.jwt.JwtConfiguration;
import com.bluementors.security.jwt.JwtTokenProvider;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.bluementors.AppCodes.APP_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AAResourceTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AAResource aaResource;

    @MockBean
    private UserService userService;

    @Before
    public void setpup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(aaResource).build();
    }

    @Test
    public void login__invalid_credentials() throws Exception {
        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aaRequest()))
        ).andExpect(status().is(APP_ERROR));
    }

    @Test
    public void login_valid_user__return_jwt_token() throws Exception {
        AARequest aaRequest = aaRequest();

        when(userService.findByEmail(aaRequest.getEmail()))
                .thenReturn(new User.Builder()
                        .email("user@bluementors.com")
                        .authenticationString(passwordEncoder.encode("123456"))
                        .build()
                );

        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("THE_TOKEN");

        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aaRequest))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(jwtConfiguration.jwtTokenName, "THE_TOKEN"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        assertThat(auth).isNotNull();
    }

    @Test
    public void logout__removes_jwt_cookie() throws Exception {
        this.mockMvc.perform(
                get("/api/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(cookie().doesNotExist(jwtConfiguration.jwtTokenName));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        assertThat(auth).isNull();
    }

    @Test
    public void login_logout__removed_context_authentication() throws Exception {
        AARequest aaRequest = aaRequest();

        when(userService.findByEmail(aaRequest.getEmail()))
                .thenReturn(new User.Builder()
                        .email("user@bluementors.com")
                        .authenticationString(passwordEncoder.encode("123456"))
                        .build()
                );

        when(jwtTokenProvider.generateToken(any(Authentication.class))).thenReturn("THE_TOKEN");

        this.mockMvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(aaRequest))
        )
                .andExpect(status().isOk())
                .andExpect(cookie().value(jwtConfiguration.jwtTokenName, "THE_TOKEN"));


        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();

        this.mockMvc.perform(
                get("/api/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(cookie().doesNotExist(jwtConfiguration.jwtTokenName));


        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    private AARequest aaRequest() {
        AARequest aaRequest = new AARequest();
        aaRequest.setEmail("user@bluementors.com");
        aaRequest.setPassword("123456");
        return aaRequest;
    }

}

package com.bluementors;

import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan("com.bluementors")
public class TestAppContextConfig {

    @MockBean
    JavaMailSender javaMailSender;
}

package com.bluementors;

import com.bluementors.bluemedia.filestorage.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.mentor.search.mentor",
        "com.bleumedia",
        "com.bluementors",
        "com.bluementors.mentor"})
@EnableJpaRepositories(basePackages = {"com.bluementors",
        "com.bleumedia"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
@EnableConfigurationProperties({FileStorageProperties.class})
public class WebServicesMonolithApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServicesMonolithApplication.class, args);
    }


    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}

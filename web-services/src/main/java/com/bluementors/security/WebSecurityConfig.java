package com.bluementors.security;

import com.bluementors.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String[] permitted = {"/h2/**", "/actuator/**", "/api/auth/**", "/version", "/**.ico"};

    @Autowired
    private WebSecurityUserDetailsService webSecurityUserDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                // Un-secure H2 Database
                .antMatchers(permitted).permitAll()
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(permitted);
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.userDetailsService(webSecurityUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
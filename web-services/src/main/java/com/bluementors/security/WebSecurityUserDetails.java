package com.bluementors.security;

import com.bluementors.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class WebSecurityUserDetails implements UserDetails {

    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private String email;
    private String authenticationString;

    public static UserDetails create(User user) {
        WebSecurityUserDetails customUserDetails = new WebSecurityUserDetails();
        customUserDetails.email = user.getEmail();
        customUserDetails.authenticationString = user.getAuthenticationString();

        customUserDetails.authorities.add(new SimpleGrantedAuthority(AppRoles
                .Names.USER));

        customUserDetails.authorities.add(new SimpleGrantedAuthority(AppRoles.Names.ADMIN));

        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return authenticationString;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

package com.cakefactory.model.security;

import com.cakefactory.model.entity.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UserCredentials userCredentials;

    public CustomUserDetails(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userCredentials.getUser().getAuthorities()
                .stream()
                .map(auth->(GrantedAuthority)()->auth.getAuthority())
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userCredentials.getPassword();
    }

    @Override
    public String getUsername() {
        return userCredentials.getEmail();
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
        return userCredentials.getEnabled();
    }
}

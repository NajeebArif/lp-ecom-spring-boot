package com.cakefactory.utils;

import com.cakefactory.model.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityHelper {


    public static void setSecurityContext(User user) {
        SecurityContextHolder.getContext()
                .setAuthentication(getAuthentication(user));
    }

    private static UsernamePasswordAuthenticationToken getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(user, null, getAuths(user));
    }

    private static List<GrantedAuthority> getAuths(User user){
        return user.getAuthorities()
                .stream()
                .map(a->new SimpleGrantedAuthority(a.getAuthority())).collect(Collectors.toList());
    }
}

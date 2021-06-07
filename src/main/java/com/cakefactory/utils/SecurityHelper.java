package com.cakefactory.utils;

import com.cakefactory.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SecurityHelper {

    public static void setSecurityContext(User user) {
        SecurityContextHolder.getContext()
                .setAuthentication(getAuthentication(user));
    }

    public static Optional<String> loggedInUserEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = null;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        return Optional.ofNullable(email);
    }

    private static UsernamePasswordAuthenticationToken getAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(Mappers.mapUserToCustomUserDetails(user), null, getAuths(user));
    }

    private static List<GrantedAuthority> getAuths(User user){
        return user.getAuthorities()
                .stream()
                .map(a->new SimpleGrantedAuthority(a.getAuthority())).collect(Collectors.toList());
    }
}

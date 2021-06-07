package com.cakefactory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/")
            .and()
                .logout().logoutSuccessUrl("/").invalidateHttpSession(true)
            .and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/users/signup", "/users/login", "/", "/vendor/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.cakefactory.config;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.model.security.FacebookLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String EDIT_IT = "Change It";
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/")
            .and()
                .oauth2Login()
                .loginPage("/users/login")
                .successHandler(getFacebookSuccessHandler())
            .and()
                .logout().logoutSuccessUrl("/").invalidateHttpSession(true)
            .and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/users/signup", "/users/login", "/", "/vendor/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                .anyRequest().authenticated();
    }

    private AuthenticationSuccessHandler getFacebookSuccessHandler() {
        return (request, response, authentication) -> processSuccessfulFacebookLogin(response, authentication);
    }

    private void processSuccessfulFacebookLogin(javax.servlet.http.HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException {
        final Object principal = authentication.getPrincipal();
        if(principal instanceof OAuth2User){
            OAuth2User user = (OAuth2User) principal;
            final String email = user.getName();
            final String fullName = user.getAttribute("name");
            final UserDto userDto = createUserDto(email, fullName);
            publishFacebookLoginEvent(userDto);
            response.sendRedirect("/");
        }
    }

    private UserDto createUserDto(String email, String fullName) {
        final String[] fullNameArray = fullName.split(" ");
        var firstName = fullNameArray[0];
        var lastName = getLastName(fullNameArray);
        return UserDto.builder().email(email).userName(email)
                .firstName(firstName).lastName(lastName)
                .addressLine1(EDIT_IT)
                .addressLine2(EDIT_IT)
                .phoneNumber(EDIT_IT)
                .age(18)
                .city(EDIT_IT)
                .postCode(EDIT_IT)
                .password("password").build();
    }

    private String getLastName(String[] fullNameArray) {
        return fullNameArray.length == 2 ? fullNameArray[1] : (fullNameArray.length == 3 ? fullNameArray[2] : fullNameArray[0]);
    }

    public void publishFacebookLoginEvent(UserDto userDto){
        FacebookLoginEvent loginEvent = new FacebookLoginEvent(this,userDto);
        eventPublisher.publishEvent(loginEvent);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

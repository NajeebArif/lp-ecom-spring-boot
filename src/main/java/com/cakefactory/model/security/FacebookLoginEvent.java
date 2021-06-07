package com.cakefactory.model.security;

import com.cakefactory.model.dto.UserDto;
import org.springframework.context.ApplicationEvent;

public class FacebookLoginEvent extends ApplicationEvent {

    private String name;

    private UserDto userDto;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public FacebookLoginEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public FacebookLoginEvent(Object source, UserDto userDto) {
        super(source);
        this.userDto = userDto;
    }

    public String getName() {
        return name;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}

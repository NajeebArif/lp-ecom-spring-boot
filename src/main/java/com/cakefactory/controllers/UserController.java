package com.cakefactory.controllers;

import com.cakefactory.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping("/signup")
    public String serverSignupPage(){
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(UserDto userDto){
        log.info(userDto.toString());
        return "redirect:/";
    }
}

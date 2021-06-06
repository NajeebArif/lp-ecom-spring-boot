package com.cakefactory.controllers;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/signup")
    public String serverSignupPage(){
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model){
        log.info(userDto.toString());
        final UserDto loggedInUser = accountService.registerUser(userDto);
        model.addAttribute("loggedInUser",loggedInUser);
        return "redirect:/";
    }
}

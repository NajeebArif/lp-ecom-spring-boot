package com.cakefactory.controllers;

import com.cakefactory.model.dto.UserDto;
import com.cakefactory.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String userProfile(Model model){
        model.addAttribute("loggedInUser",accountService.getLoggedInUser());
        return "user";
    }

    @GetMapping("/signup")
    public String serverSignupPage(){
        return "signup";
    }

    @GetMapping("/login")
    public String renderLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        accountService.logout();
        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String performLogin(@RequestParam String email, @RequestParam String password){
//        accountService.logIn(email, password);
//        return "redirect:/";
//    }


    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model){
        log.info(userDto.toString());
        final UserDto loggedInUser = accountService.registerUser(userDto);
        model.addAttribute("loggedInUser",loggedInUser);
        return "redirect:/";
    }
}

package com.cakefactory.controllers;

import com.cakefactory.service.AccountService;
import com.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basket")
@Slf4j
public class BasketController {

    private final BasketService basketService;
    private final AccountService accountService;

    public BasketController(BasketService basketService, AccountService accountService) {
        this.basketService = basketService;
        this.accountService = accountService;
    }

    @GetMapping
    public String renderBasketPage(Model model){
        model.addAttribute("basketCapacity", basketService.getCapacity());
        model.addAttribute("basketItems", basketService.getBasketItems());
        model.addAttribute("loggedInUser", accountService.getLoggedInUser());
        return "basket";
    }

    @PostMapping
    public String addToBasket(@RequestParam String sku){
        basketService.addToBasket(sku);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addSingleBasket(@RequestParam String sku){
        basketService.addToBasket(sku);
        return "redirect:/basket";
    }

    @PostMapping("/delete")
    public String deleteFromBasket(@RequestParam String sku){
        log.info("item with sku deleted: {}",sku);
        basketService.removeAllItems(sku);
        return "redirect:/basket";
    }

    @PostMapping("/delete/one")
    public String deleteOneFromBasket(@RequestParam String sku){
        basketService.removeSingleItem(sku);
        return "redirect:/basket";
    }
}

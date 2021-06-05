package com.cakefactory.controllers;

import com.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basket")
@Slf4j
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public String addToBasket(@RequestParam String sku){
        log.info("add to cart clicked. SKU: {}",sku);
        basketService.addToBasket(sku);
        return "redirect:/";
    }
}

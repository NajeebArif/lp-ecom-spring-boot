package com.cakefactory.controllers;

import com.cakefactory.service.AccountService;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CatalogController {

    private final CatalogService catalogService;
    private final BasketService basketService;
    private final AccountService accountService;

    public CatalogController(CatalogService catalogService, BasketService basketService, AccountService accountService) {
        this.catalogService = catalogService;
        this.basketService = basketService;
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String displayCatalog(Model model){
        final var items = catalogService.getItems();
        model.addAttribute("items", items);
        model.addAttribute("basketCapacity",basketService.getCapacity());
        model.addAttribute("loggedInUser",accountService.getLoggedInUser());
        return "catalog";
    }

}

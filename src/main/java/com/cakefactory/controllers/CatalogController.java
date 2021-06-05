package com.cakefactory.controllers;

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

    public CatalogController(CatalogService catalogService, BasketService basketService) {
        this.catalogService = catalogService;
        this.basketService = basketService;
    }

    @GetMapping("/")
    public String displayCatalog(Model model){
        final var items = catalogService.getItems();
        model.addAttribute("items", items);
        model.addAttribute("basketCapacity",basketService.getCapacity());
        return "catalog";
    }

}

package com.cakefactory.controllers;

import com.cakefactory.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    public String displayCatalog(Model model){
        final var items = catalogService.getItems();
        model.addAttribute("items", items);
        return "catalog";
    }

}

package com.cakefactory.controllers;

import com.cakefactory.service.Catalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private final Catalog catalog;

    public CatalogController(Catalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping
    public String displayCatalog(Model model){
        final var items = catalog.getItems();
        model.addAttribute("items", items);
        return "catalogPage";
    }

}

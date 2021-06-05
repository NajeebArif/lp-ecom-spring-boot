package com.cakefactory.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BasketController.class)
@AutoConfigureMockMvc
@Slf4j
class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST method on /basket should be map to add to basket method.")
    public void testAddToBasket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket").param("sku","abc"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
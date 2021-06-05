package com.cakefactory.controllers;

import com.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BasketController.class)
@AutoConfigureMockMvc
@Slf4j
class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @BeforeEach
    public void init(){
        BDDMockito.given(basketService.getCapacity()).willReturn(2);
    }

    @Test
    @DisplayName("Given basket capacity as 2, should be rendered on the page.")
    public void testBasketCapacity() throws Exception {
        mockMvc.perform(get("/basket"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(Matchers.containsString("Current basket capacity: 2")));
    }

    @Test
    @DisplayName("render basket page")
    public void testBasketPage() throws Exception {
        mockMvc.perform(get("/basket"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(Matchers.containsString("Your Basket")));
    }

    @Test
    @DisplayName("POST method on /basket should be map to add to basket method.")
    public void testAddToBasket() throws Exception {
        mockMvc.perform(post("/basket").param("sku","abc"))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().is3xxRedirection());
    }
}
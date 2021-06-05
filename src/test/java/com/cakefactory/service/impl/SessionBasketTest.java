package com.cakefactory.service.impl;

import com.cakefactory.model.dto.BasketItem;
import com.cakefactory.model.dto.ItemDto;
import com.cakefactory.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SessionBasketTest {

    private CatalogService catalogService = Mockito.mock(CatalogService.class);
    final SessionBasket sessionBasket = new SessionBasket(catalogService);

    @BeforeEach
    public void init(){
        BDDMockito.given(catalogService.findItemBySku("abc")).willReturn(new ItemDto("abc","testTitle", BigDecimal.ZERO));
    }

    @Test
    @DisplayName("calling remove for a key deletes the entry from the basket")
    public void testRemove(){
        sessionBasket.addToBasket("abc");
        sessionBasket.addToBasket("abc");
        final List<BasketItem> basketItems = sessionBasket.getBasketItems();
        assertThat(basketItems).hasSize(1)
                .first()
                .matches(b->b.getQty().equals(2));
        sessionBasket.removeAllItems("abc");
        final List<BasketItem> items = sessionBasket.getBasketItems();
        assertThat(items).isEmpty();
    }

    @Test
    @DisplayName("Get basket items returns all the basket items.")
    public void testBasketItems(){
        sessionBasket.addToBasket("abc");
        sessionBasket.addToBasket("abc");
        final List<BasketItem> basketItems = sessionBasket.getBasketItems();
        assertThat(basketItems).hasSize(1)
                .first()
                .matches(b->b.getQty().equals(2));
    }

    @Test
    @DisplayName("adding to basket should increase the count by 1")
    public void addToBasketIncCount(){
        sessionBasket.addToBasket("abc");
        assertThat(sessionBasket.getCapacity()).isEqualTo(1);
        sessionBasket.addToBasket("abc");
        assertThat(sessionBasket.getCapacity()).isEqualTo(2);
    }
}
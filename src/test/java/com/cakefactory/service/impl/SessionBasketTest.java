package com.cakefactory.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionBasketTest {

    @Test
    @DisplayName("adding to basket should increase the count by 1")
    public void addToBasketIncCount(){
        final SessionBasket sessionBasket = new SessionBasket();
        sessionBasket.addToBasket("abc");
        assertThat(sessionBasket.getCapacity()).isEqualTo(1);
        sessionBasket.addToBasket("abc");
        assertThat(sessionBasket.getCapacity()).isEqualTo(2);
    }
}
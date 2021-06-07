package com.cakefactory.service.impl;

import com.cakefactory.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

class OrderServiceImplTest {

    BasketService basketService = Mockito.mock(BasketService.class);

    OrderServiceImpl orderService = new OrderServiceImpl(basketService, null);

    @BeforeEach
    public void init(){
        willDoNothing().given(basketService).clear();
    }

//    @Test
//    @DisplayName("placing the order clears the basket.")
//    public void testPlaceOrderClearsBasket(){
//        orderService.placeOrder();
//        verify(basketService).clear();
//    }

}
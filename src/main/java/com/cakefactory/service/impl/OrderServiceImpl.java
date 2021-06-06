package com.cakefactory.service.impl;

import com.cakefactory.model.dto.OrderDetailsDto;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final BasketService basketService;

    public OrderServiceImpl(BasketService basketService) {
        this.basketService = basketService;
    }

    @Override
    public OrderDetailsDto placeOrder() {
        final OrderDetailsDto orderDetailsDto = OrderDetailsDto.builder().orderId(UUID.randomUUID())
                .placedAt(LocalDateTime.now())
                .orderedItems(basketService.getBasketItems())
                .build();
        basketService.clear();
        return orderDetailsDto;
    }
}

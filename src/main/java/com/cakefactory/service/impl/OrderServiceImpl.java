package com.cakefactory.service.impl;

import com.cakefactory.model.PendingPayment;
import com.cakefactory.model.dto.OrderDetailsDto;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.OrderService;
import com.cakefactory.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final BasketService basketService;

    private final PaymentService paymentService;

    public OrderServiceImpl(BasketService basketService, PaymentService paymentService) {
        this.basketService = basketService;
        this.paymentService = paymentService;
    }

    @Override
    public PendingPayment placeOrder(URI returnUri) {
        final OrderDetailsDto orderDetailsDto = getOrderDetails();
        final Double totalCost = totalCost(orderDetailsDto);
        final PendingPayment pendingPayment = paymentService.create(totalCost, returnUri);
        return pendingPayment;
    }



    public OrderDetailsDto getOrderDetails() {
        final OrderDetailsDto orderDetailsDto = OrderDetailsDto.builder().orderId(UUID.randomUUID())
                .placedAt(LocalDateTime.now())
                .orderedItems(basketService.getBasketItems())
                .build();
        return orderDetailsDto;
    }

    @Override
    public Double totalCost(OrderDetailsDto orderDetailsDto) {
        return orderDetailsDto.getOrderedItems().stream().mapToDouble(b -> b.totalPrice().doubleValue()).sum();
    }

    @Override
    public void complete(String token) {

    }
}

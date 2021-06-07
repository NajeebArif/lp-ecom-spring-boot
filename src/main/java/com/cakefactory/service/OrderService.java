package com.cakefactory.service;

import com.cakefactory.model.PendingPayment;
import com.cakefactory.model.dto.OrderDetailsDto;

import java.net.URI;

public interface OrderService {

    PendingPayment placeOrder(URI returnUri);

    OrderDetailsDto getOrderDetails();

    Double totalCost(OrderDetailsDto orderDetailsDto);

    void complete(String payPalOrderId);
}

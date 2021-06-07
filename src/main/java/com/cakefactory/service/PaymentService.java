package com.cakefactory.service;

import com.cakefactory.model.PendingPayment;

import java.net.URI;

public interface PaymentService {

    PendingPayment create(Double totalCost, URI returnUri);
    String complete(String orderId);
}

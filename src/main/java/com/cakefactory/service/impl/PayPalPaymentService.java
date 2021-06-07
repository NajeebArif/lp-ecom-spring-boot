package com.cakefactory.service.impl;

import com.cakefactory.model.PendingPayment;
import com.cakefactory.service.PaymentService;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.orders.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PayPalPaymentService implements PaymentService {

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;

    private PayPalHttpClient payPalClient;

    private final String APPROVE_LINK_REL = "approve";

    @PostConstruct
    public void init() {
        final PayPalEnvironment.Sandbox sandbox = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        payPalClient = new PayPalHttpClient(sandbox);
    }

    @Override
    @SneakyThrows
    public PendingPayment create(Double totalCost, URI returnUri) {
        final OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value("100"));
        purchaseUnits.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnits);
        orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUri.toString()));
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> response = payPalClient.execute(ordersCreateRequest);
        final Order order = response.result();
        LinkDescription approveUri = order.links().stream().filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return new PendingPayment(order.id(), URI.create(approveUri.href()));
    }

    @SneakyThrows
    @Override
    public String complete(String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        HttpResponse<com.paypal.orders.Order> response = payPalClient.execute(request);
        return response.result().status();
    }
}

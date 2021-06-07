package com.cakefactory.controllers;

import com.cakefactory.model.PendingPayment;
import com.cakefactory.model.dto.OrderDetailsDto;
import com.cakefactory.service.AccountService;
import com.cakefactory.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final AccountService accountService;

    public OrderController(OrderService orderService, AccountService accountService) {
        this.orderService = orderService;
        this.accountService = accountService;
    }

    @GetMapping
    public String renderOrderDetails(Model model){
        final OrderDetailsDto orderDetails = orderService.getOrderDetails();
        final Double totalCost = orderService.totalCost(orderDetails);
        model.addAttribute("orderDetails",orderDetails);
        setModelAttributes(model,0,totalCost);
        return "order";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        final OrderDetailsDto orderDetails = orderService.getOrderDetails();
        model.addAttribute("orderDetails",orderDetails);
        final double totalCost = orderService.totalCost(orderDetails);
        setModelAttributes(model,orderDetails.getOrderedItems().size(), totalCost);
        return "checkout";
    }

    @PostMapping
    public String placeOrder(Model model, HttpServletRequest request){
        final OrderDetailsDto orderDetailsDto = null;
        final PendingPayment pendingPayment = orderService.placeOrder(buildReturnUrl(request));
        final URI approveUri = pendingPayment.getApproveUri();
        return "redirect:"+approveUri;
    }

    @GetMapping("/complete")
    String completeOrder(@RequestParam String token, Model model) {
        try {
            orderService.complete(token);
            return "redirect:/order";
        } catch (Exception e) {
            log.error("Failed to complete order", e);
            return "redirect:/";
        }
    }

    private URI buildReturnUrl(HttpServletRequest request) {
        try {
            URI requestUri = URI.create(request.getRequestURL().toString());
            return new URI(requestUri.getScheme(), requestUri.getUserInfo(), requestUri.getHost(), requestUri.getPort(), "/order/complete", null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void setModelAttributes(Model model, int basketCapacity, double totalCost1) {
        model.addAttribute("basketCapacity", basketCapacity);
        model.addAttribute("loggedInUser", accountService.getLoggedInUser());
        model.addAttribute("totalCost", totalCost1);
    }
}

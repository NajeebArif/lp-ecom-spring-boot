package com.cakefactory.controllers;

import com.cakefactory.model.dto.OrderDetailsDto;
import com.cakefactory.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String placeOrder(Model model){
        final OrderDetailsDto orderDetailsDto = orderService.placeOrder();
        model.addAttribute("orderDetails",orderDetailsDto);
        model.addAttribute("basketCapacity",0);
        model.addAttribute("totalCost",orderDetailsDto.getOrderedItems().stream().mapToDouble(b->b.totalPrice().doubleValue()).sum());
        return "order";
    }
}

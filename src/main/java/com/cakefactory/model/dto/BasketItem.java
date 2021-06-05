package com.cakefactory.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketItem {

    private ItemDto itemDto;
    private Integer qty;

    public BigDecimal totalPrice(){
        return itemDto.getPrice().multiply(BigDecimal.valueOf(qty));
    }

}

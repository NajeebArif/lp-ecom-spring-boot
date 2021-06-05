package com.cakefactory.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class BasketItem {

    private ItemDto itemDto;
    private Integer qty;

    public BigDecimal totalPrice(){
        return itemDto.getPrice().multiply(BigDecimal.valueOf(qty));
    }

}

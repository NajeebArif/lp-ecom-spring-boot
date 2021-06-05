package com.cakefactory.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemDto {
    private final String sku;
    private final String title;
    private final BigDecimal price;

}

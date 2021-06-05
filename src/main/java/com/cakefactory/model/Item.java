package com.cakefactory.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {
    private final Long id;
    private final String title;
    private final BigDecimal price;

}

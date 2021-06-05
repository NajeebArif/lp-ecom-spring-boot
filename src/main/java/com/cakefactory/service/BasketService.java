package com.cakefactory.service;

import com.cakefactory.model.dto.BasketItem;

import java.util.List;

public interface BasketService {

    void addToBasket(final String sku);

    Integer getCapacity();

    List<BasketItem> getBasketItems();

    void removeItem(String sku);
}

package com.cakefactory.service;

public interface BasketService {

    void addToBasket(final String sku);

    Integer getCapacity();
}

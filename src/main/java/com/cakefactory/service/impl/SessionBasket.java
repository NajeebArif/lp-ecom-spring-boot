package com.cakefactory.service.impl;

import com.cakefactory.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@Slf4j
@SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
public class SessionBasket implements BasketService {

    private Integer count = 0;

    @Override
    public void addToBasket(String sku) {
        count++;
        log.info("Item added to cart.");
    }

    @Override
    public Integer getCapacity() {
        return count;
    }
}

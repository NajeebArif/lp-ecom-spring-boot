package com.cakefactory.service.impl;

import com.cakefactory.model.dto.BasketItem;
import com.cakefactory.model.dto.ItemDto;
import com.cakefactory.service.BasketService;
import com.cakefactory.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@SessionScope(proxyMode = ScopedProxyMode.INTERFACES)
public class SessionBasket implements BasketService {

    private Integer count = 0;
    private final CatalogService catalogService;

    private Map<String, BasketItem> basketItemMap = new ConcurrentHashMap<>();

    public SessionBasket(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public void addToBasket(String sku) {
        final ItemDto itemBySku = catalogService.findItemBySku(sku);
        count++;
        final BasketItem basketItem = basketItemMap.get(sku);
        final BasketItem item = new BasketItem();
        item.setItemDto(itemBySku);
        if(basketItem==null){
            item.setQty(1);
        }else{
            item.setQty(basketItem.getQty()+1);
        }
        basketItemMap.put(sku,item);
        log.info("Item added to cart.");
    }

    @Override
    public Integer getCapacity() {
        return basketItemMap.values().stream().mapToInt(BasketItem::getQty).sum();
    }

    @Override
    public List<BasketItem> getBasketItems() {
        return new ArrayList<>(basketItemMap.values());
    }

    @Override
    public void removeAllItems(String sku) {
        basketItemMap.remove(sku);
    }

    @Override
    public void removeSingleItem(String sku) {
        final BasketItem item = basketItemMap.get(sku);
        if(item!=null){
            basketItemMap.remove(sku);
            final BasketItem basketItem = new BasketItem(item.getItemDto(), item.getQty() - 1);
            if(basketItem.getQty()>0)
                basketItemMap.put(sku,basketItem);
        }
    }
}

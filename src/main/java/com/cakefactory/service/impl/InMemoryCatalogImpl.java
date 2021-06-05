package com.cakefactory.service.impl;

import com.cakefactory.model.Item;
import com.cakefactory.service.Catalog;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class InMemoryCatalogImpl implements Catalog {

        final List<Item> items = Arrays.asList(
                new Item(1l, "testCake1", BigDecimal.valueOf(20.2)),
                new Item(2l, "testCake2", BigDecimal.valueOf(20.2)),
                new Item(3l, "testCake3", BigDecimal.valueOf(20.2)),
                new Item(4l, "testCake4", BigDecimal.valueOf(20.2)),
                new Item(5l, "testCake5", BigDecimal.valueOf(20.2))
        );

    @Override
    public List<Item> getItems() {
        return items;
    }
}

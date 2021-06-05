package com.cakefactory.service.impl;

import com.cakefactory.model.dto.ItemDto;
import com.cakefactory.model.mappers.ItemMapper;
import com.cakefactory.repository.ItemsRepo;
import com.cakefactory.service.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaCatalogService implements CatalogService {

    private final ItemsRepo itemsRepo;

    public JpaCatalogService(ItemsRepo itemsRepo) {
        this.itemsRepo = itemsRepo;
    }

    @Override
    public List<ItemDto> getItems() {
        return itemsRepo.findAll()
                .stream()
                .map(ItemMapper::mapItemToItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto findItemBySku(String sku) {
        return itemsRepo.findById(sku)
                .map(ItemMapper::mapItemToItemDto)
                .orElseThrow(()->new RuntimeException("No Item found"));
    }
}

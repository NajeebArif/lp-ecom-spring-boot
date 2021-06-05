package com.cakefactory.service;

import com.cakefactory.model.dto.ItemDto;

import java.util.List;

public interface CatalogService {
    List<ItemDto> getItems();
}

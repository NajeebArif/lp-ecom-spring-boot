package com.cakefactory.model.mappers;

import com.cakefactory.model.dto.ItemDto;
import com.cakefactory.model.entity.Item;

public class ItemMapper {

    public static Item mapItemDtoToItem(final ItemDto dto){
        return new Item(dto.getSku(), dto.getTitle(), dto.getPrice());
    }

    //FIXME(marking ids as null? is that a good idea?)-> it removes the internal id's from the Ui Layer.
    public static ItemDto mapItemToItemDto(final Item item){
        return new ItemDto(item.getSku(), item.getTitle(), item.getPrice());
    }
}

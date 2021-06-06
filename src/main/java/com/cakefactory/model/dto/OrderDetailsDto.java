package com.cakefactory.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDetailsDto {

    private UUID orderId;
    private LocalDateTime placedAt;
    List<BasketItem> orderedItems;
}

package com.coffe_management_system.dto.item;

import com.coffe_management_system.entity.item.ItemEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private String image;
    private Integer price;

    public static ItemResponse fromEntity(ItemEntity entity) {
        return ItemResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .categoryId(entity.getCategoryId())
                .image(entity.getImage())
                .price(entity.getPrice())
                .build();
    }

}

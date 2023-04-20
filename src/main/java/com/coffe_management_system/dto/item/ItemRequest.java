package com.coffe_management_system.dto.item;

import lombok.Data;

@Data
public class ItemRequest {
    private Long id;
    private String name;
    private Long categoryId;
    private Integer price;

}

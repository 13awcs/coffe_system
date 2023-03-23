package com.coffe_management_system.dto.item;

public interface ItemResponseProjection {
    Long getId();
    String getName();
    Long getCategoryId();
    String getImage();
    Integer getPrice();
}

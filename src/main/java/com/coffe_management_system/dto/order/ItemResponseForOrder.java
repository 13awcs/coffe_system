package com.coffe_management_system.dto.order;

public interface ItemResponseForOrder {
    Long getId();
    Long getOrderId();
    Long getItemId();
    String getName();
    int getQuantity();
    Double getPrice();
}

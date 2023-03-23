package com.coffe_management_system.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long id;
    private List<ItemRequestForOrder> listItemRequest;
    private String note;
    private Long tableId;
}

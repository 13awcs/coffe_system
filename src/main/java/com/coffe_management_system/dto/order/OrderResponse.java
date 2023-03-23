package com.coffe_management_system.dto.order;

import com.coffe_management_system.entity.order.OrderEntity;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@Builder
public class OrderResponse {
    private Long id;
    private List<ItemResponseForOrder> listItemResponse;
    private String note;
    private Double totalPrice;
    private Long tableId;
    private boolean status;

    public static OrderResponse fromEntity(OrderEntity entity) {
        return OrderResponse.builder()
                .id(entity.getId())
                .note(entity.getNote())
                .totalPrice(entity.getTotalPrice())
                .tableId(entity.getTableId())
                .status(entity.isStatus())
                .build();
    }

}

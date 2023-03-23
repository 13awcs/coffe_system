package com.coffe_management_system.dto.payment;

import com.coffe_management_system.dto.order.ItemResponseForOrder;
import com.coffe_management_system.entity.order.BillEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillResponse {
    private Long id;
    private Long orderId;
    private Long tableId;
    private List<ItemResponseForOrder> listItem;
    private Double discount;
    private Double finalPrice;
    private Long customerId;
    private Long employeeId;
    private Date createTime;

    public static BillResponse fromEntity(BillEntity entity) {
        return BillResponse.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .discount(entity.getDiscount())
                .finalPrice(entity.getFinalPrice())
                .employeeId(entity.getEmployeeId())
                .createTime(entity.getCreateTime())
                .build();
    }

}

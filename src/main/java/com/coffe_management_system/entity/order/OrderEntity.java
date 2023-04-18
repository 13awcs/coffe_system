package com.coffe_management_system.entity.order;

import com.coffe_management_system.dto.order.OrderRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "o_order")
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    private Long tableId;
    private Double totalPrice;
    private Long employeeId;
    private Date createTime;
    private boolean status = false;
    private Long storeId;

    public static OrderEntity initInstance(OrderRequest request) {
        OrderEntity entity =  OrderEntity.builder()
                .id(request.getId())
                .note(request.getNote())
                .employeeId(request.getEmployeeId())
                .tableId(request.getTableId())
                .build();
        final Date now = new Date();
        entity.setCreateTime(now);
        return entity;
    }
    public OrderEntity with(OrderRequest request) {
        OrderEntity entity = new OrderEntity();
        entity.setId(request.getId());
        entity.setNote(request.getNote());
        entity.setTableId(request.getTableId());
        entity.setEmployeeId(request.getEmployeeId());
        entity.setCreateTime(new Date());
        return entity;
    }
}

package com.coffe_management_system.entity.order;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Double discount;
    private Double finalPrice;
    private Long customerId;

    @Convert(converter = PaymentMethod.Converter.class)
    private PaymentMethod paymentMethod;

    private Long employeeId;
    private Long storeId;
    private boolean isHasCustomer;
    private Date createTime;
}

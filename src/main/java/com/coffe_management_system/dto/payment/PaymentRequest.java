package com.coffe_management_system.dto.payment;

import com.coffe_management_system.entity.order.PaymentMethod;
import lombok.Data;

import javax.persistence.Convert;

@Data
public class PaymentRequest {
    private Long tableId;
    private Long customerId;
    private Long employeeId;

    @Convert(converter = PaymentMethod.Converter.class)
    private PaymentMethod paymentMethod;
}

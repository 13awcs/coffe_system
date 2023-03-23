package com.coffe_management_system.entity.order;

import com.coffe_management_system.util.enum_helper.AbstractEnumConverter;
import com.coffe_management_system.util.enum_helper.ValueEnumInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentMethod implements ValueEnumInterface<Integer> {
    CASH(1), BANK_TRANSFER(2), CARD(3);
    public final int value;

    @Override
    public Integer getValue() {
        return value;
    }

    public static class Converter extends AbstractEnumConverter<PaymentMethod, Integer> {
        public Converter() {
            super(PaymentMethod.class);
        }
    }
}

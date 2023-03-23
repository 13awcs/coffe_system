package com.coffe_management_system.entity.customer;

import com.coffe_management_system.util.enum_helper.AbstractEnumConverter;
import com.coffe_management_system.util.enum_helper.ValueEnumInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeCustomer implements ValueEnumInterface<Double> {
    NORMAL(0.0), SILVER(0.05), GOLD(0.1), PLATINUM(0.15);
    public final double value;

    @Override
    public Double getValue() {
        return value;
    }

    public static class Converter extends AbstractEnumConverter<TypeCustomer, Double> {
        public Converter() {
            super(TypeCustomer.class);
        }
    }
}

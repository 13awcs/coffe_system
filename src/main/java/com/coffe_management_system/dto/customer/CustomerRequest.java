package com.coffe_management_system.dto.customer;

import lombok.Data;

@Data
public class CustomerRequest {
    private Long id;
    private String name;
    private String phone;
    private int point = 1;
}

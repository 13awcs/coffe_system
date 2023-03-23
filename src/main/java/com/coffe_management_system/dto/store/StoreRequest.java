package com.coffe_management_system.dto.store;

import lombok.Data;

@Data
public class StoreRequest {
    private Long id;
    private String name;
    private String address;
}

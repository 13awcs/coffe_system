package com.coffe_management_system.dto.table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class TableRequest {
    private Long id;
    private String name;
}

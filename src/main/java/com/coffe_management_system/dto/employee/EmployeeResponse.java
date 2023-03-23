package com.coffe_management_system.dto.employee;

import com.coffe_management_system.entity.employee.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeResponse {
    private Long id;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private String store;
    private String shift;

    public static EmployeeResponse fromEntity(EmployeeEntity entity) {
        return EmployeeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dob(entity.getDob())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .build();
    }
}

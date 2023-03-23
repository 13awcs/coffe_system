package com.coffe_management_system.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {
    private Long id;
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private Long storeId;
    private Long shiftId;
}

package com.coffe_management_system.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {
    private Long id;
    private String name;

    private Date dob;
    private String address;
    private String phone;
    private String email;
    private Long shiftId;
    private Long storeId;
}

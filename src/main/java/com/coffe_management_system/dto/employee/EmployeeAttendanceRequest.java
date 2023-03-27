package com.coffe_management_system.dto.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class EmployeeAttendanceRequest {
    private Long id;
    private String date;
    private Long employeeId;
    private Date checkIn;
    private Date checkOut;
}

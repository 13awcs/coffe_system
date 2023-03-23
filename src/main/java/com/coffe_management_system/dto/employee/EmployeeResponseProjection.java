package com.coffe_management_system.dto.employee;

import java.util.Date;

public interface EmployeeResponseProjection {
    Long getId();
    String getName();
    Date getDob();
    String getAddress();
    String getPhone();
    String getEmail();
    String getStore();
    String getShift();
}

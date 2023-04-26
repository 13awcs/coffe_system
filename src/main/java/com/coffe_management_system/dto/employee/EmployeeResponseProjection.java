package com.coffe_management_system.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public interface EmployeeResponseProjection {
    Long getId();
    String getName();

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'")
    @JsonFormat(pattern = "MM/dd/yyyy")
    Date getDob();
    String getAddress();
    String getPhone();
    String getEmail();
    Long getStoreId();
    Long getShiftId();
    String getShiftName();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    Date getCreateTime();
}

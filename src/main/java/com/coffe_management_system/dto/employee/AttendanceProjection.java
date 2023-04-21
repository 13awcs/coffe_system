package com.coffe_management_system.dto.employee;

import java.util.Date;

public interface AttendanceProjection {
    String getName();
    String getShiftName();
    Date getDate();
    Date getCheckin();
    Date getCheckout();

}

package com.coffe_management_system.repository.employee;

import com.coffe_management_system.entity.employee.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, Long> {

    @Query(value = "select et.id, et.employee_id, et.date, et.check_in, et.check_out, et.total_hour " +
            "from employee_attendance et " +
            "where et.employee_id = ?1 and DATE_FORMAT(et.date, '%Y/%m/%d') = ?2", nativeQuery = true)
    EmployeeAttendanceEntity findByEmployeeIdAndDate(Long employeeId, String date);
}

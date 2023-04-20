package com.coffe_management_system.repository.employee;

import com.coffe_management_system.dto.employee.AttendanceProjection;
import com.coffe_management_system.entity.employee.EmployeeAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, Long> {

    @Query(value = "select et.id, et.employee_id, et.date, et.check_in, et.check_out, et.total_hour " +
            "from employee_attendance et " +
            "where et.employee_id = ?1 and DATE_FORMAT(et.date, '%Y/%m/%d') = ?2", nativeQuery = true)
    EmployeeAttendanceEntity findByEmployeeIdAndDate(Long employeeId, String date);

    @Query(value = "select em.name as name, et.date as date, et.check_in as checkin, et.check_out as checkout " +
            "from employee em " +
            "join employee_attendance et on em.id = et.employee_id " +
            "where em.store_id = ?1 ", nativeQuery = true)
    List<AttendanceProjection> getListAttendance(Long storeId);


}

package com.coffe_management_system.repository.employee;

import com.coffe_management_system.dto.employee.AttendanceDto;
import com.coffe_management_system.dto.employee.AttendanceProjection;
import com.coffe_management_system.entity.employee.EmployeeAttendanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendanceEntity, Long> {

    @Query(value = "select et.id, et.employee_id, et.date, et.check_in, et.check_out, et.total_hour " +
            "from employee_attendance et " +
            "where et.employee_id = ?1 and DATE_FORMAT(et.date, '%Y/%m/%d') = ?2", nativeQuery = true)
    EmployeeAttendanceEntity findByEmployeeIdAndDate(Long employeeId, String date);

//    @Query(value = "select em.name as name, s.name as shiftName, et.date as date, et.check_in as checkin, et.check_out as checkout " +
//            "from employee em " +
//            "join employee_attendance et on em.id = et.employee_id " +
//            "join shift s on em.shift_id = s.id " +
//            "where em.store_id = ?1 ",
//            countQuery = "select count(em.name, s.name, et.date, et.check_in, et.check_out) " +
//            "from employee em " +
//            "join employee_attendance et on em.id = et.employee_id " +
//            "join shift s on em.shift_id = s.id " +
//            "where em.store_id = ?1 ", nativeQuery = true)
//    Page<AttendanceProjection> getPageAttendance(Long storeId, Pageable pageable);


    @Query(value = "select new com.coffe_management_system.dto.employee.AttendanceDto(em.name, s.name, et.date, et.checkIn, et.checkOut) " +
            "from EmployeeEntity em " +
            "join EmployeeAttendanceEntity et on em.id = et.employeeId " +
            "join ShiftEntity s on em.shiftId = s.id " +
            "where em.storeId = ?1 ")
    Page<AttendanceDto> getPageAttendance(Long storeId, Pageable pageable);
}

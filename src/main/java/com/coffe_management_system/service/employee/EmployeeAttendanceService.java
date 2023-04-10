package com.coffe_management_system.service.employee;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.EmployeeAttendanceRequest;
import com.coffe_management_system.entity.employee.EmployeeAttendanceEntity;
import com.coffe_management_system.repository.employee.EmployeeAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EmployeeAttendanceService {
    private final EmployeeAttendanceRepository repository;

    public ServerResponseDto save(EmployeeAttendanceRequest request) {
        Long employeeId = request.getEmployeeId();
        String date = request.getDate();
        EmployeeAttendanceEntity entityToSave = new EmployeeAttendanceEntity();
        EmployeeAttendanceEntity entityFromDB = repository.findByEmployeeIdAndDate(employeeId, date);
        if(entityFromDB == null) {
            entityToSave.setDate(date);
            entityToSave.setEmployeeId(request.getEmployeeId());
            entityToSave.setCheckIn(new Date());
        }
        else {
            entityToSave.setDate(entityFromDB.getDate());
            entityToSave.setId(entityFromDB.getId());
            entityToSave.setEmployeeId(entityFromDB.getEmployeeId());
            entityToSave.setCheckIn(entityFromDB.getCheckIn());
            entityToSave.setCheckOut(entityFromDB.getCheckOut());

            System.out.println("Check out: "+entityFromDB.getCheckOut());
            System.out.println("Check in: "+entityFromDB.getCheckIn());
            entityToSave.setTotalHour((entityFromDB.getCheckOut().getTime() - entityFromDB.getCheckIn().getTime()) / 1000 / 60 / 60);
            System.err.println(entityToSave.getTotalHour());
        }
        repository.save(entityToSave);

        return ServerResponseDto.SUCCESS;
    }
}

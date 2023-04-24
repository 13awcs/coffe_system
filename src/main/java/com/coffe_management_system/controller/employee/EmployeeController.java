package com.coffe_management_system.controller.employee;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.AttendanceDto;
import com.coffe_management_system.dto.employee.AttendanceProjection;
import com.coffe_management_system.dto.employee.EmployeeRequest;
import com.coffe_management_system.dto.employee.EmployeeResponseProjection;
import com.coffe_management_system.service.employee.EmployeeAttendanceService;
import com.coffe_management_system.service.employee.EmployeeService;
import com.coffe_management_system.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("admin/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeAttendanceService attendanceService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveEmployee(@RequestBody EmployeeRequest request) {
        employeeService.saveEmployee(request);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }

    @GetMapping("/{storeId}/detail/{id}")
    public ResponseEntity<ServerResponseDto> detailEmployee(@PathVariable Long storeId, @PathVariable Long id) {
        return ResponseEntity.ok(employeeService.detailEmployee(storeId, id));
    }

    @GetMapping("/{storeId}/list")
    public ResponseEntity<Page<EmployeeResponseProjection>> getPageEmployee(@RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "20") int size,
                                                                            @RequestParam(defaultValue = "name") String sortField,
                                                                            @RequestParam(defaultValue = "desc") String sortDir,
                                                                            @PathVariable Long storeId) {
        Pageable pageable = PageUtil.getPage(sortDir, sortField, page, size);
        return ResponseEntity.ok(employeeService.getPageEmployee(storeId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServerResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping("/{storeId}/attendance/list")
    public ResponseEntity<Page<AttendanceDto>> getPageAttendance(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "20") int size,
                                                              @RequestParam(defaultValue = "date") String sortField,
                                                              @RequestParam(defaultValue = "desc") String sortDir,
                                                              @PathVariable Long storeId) {
        Pageable pageable = PageUtil.getPage(sortDir, sortField, page, size);
        return ResponseEntity.ok(attendanceService.statisticAttendance(storeId, pageable));
    }
}

package com.coffe_management_system.controller.employee;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.EmployeeRequest;
import com.coffe_management_system.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveEmployee(@RequestBody EmployeeRequest request) {
        employeeService.saveEmployee(request);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerResponseDto> detailEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.detailEmployee(id));
    }
}

package com.coffe_management_system.controller.manager;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("manager")
@RequiredArgsConstructor
public class ManagerController {
    private final EmployeeService employeeService;

    @GetMapping("/store/{employeeId}")
    public ResponseEntity<ServerResponseDto> getStore(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getStore(employeeId));
    }
}

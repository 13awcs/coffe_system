package com.coffe_management_system.auth.controller;

import com.coffe_management_system.auth.dto.RegistrationDto;
import com.coffe_management_system.auth.service.UserService;
import com.coffe_management_system.dto.ServerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCustomer(@RequestBody RegistrationDto request) {
        return ResponseEntity.ok(userService.create(request));
    }
}

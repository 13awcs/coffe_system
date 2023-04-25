package com.coffe_management_system.auth.controller;

import com.coffe_management_system.auth.dto.RegistrationDto;
import com.coffe_management_system.auth.service.UserService;
import com.coffe_management_system.dto.ServerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCustomer(@RequestBody RegistrationDto request) {
        return ResponseEntity.ok(userService.create(request));
    }
}

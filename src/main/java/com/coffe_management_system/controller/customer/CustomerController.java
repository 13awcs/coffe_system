package com.coffe_management_system.controller.customer;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.customer.CustomerRequest;
import com.coffe_management_system.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.saveCustomer(request));
    }
}

package com.coffe_management_system.controller.customer;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.customer.CustomerRequest;
import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.saveCustomer(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerEntity>> list() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
}

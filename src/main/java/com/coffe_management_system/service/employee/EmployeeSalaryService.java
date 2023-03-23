package com.coffe_management_system.service.employee;

import com.coffe_management_system.repository.employee.EmployeeSalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeSalaryService {

    private final EmployeeSalaryRepository repository;



}

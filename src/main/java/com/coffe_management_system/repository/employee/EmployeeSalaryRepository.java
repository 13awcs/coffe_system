package com.coffe_management_system.repository.employee;

import com.coffe_management_system.entity.employee.EmployeeSalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryEntity, Long> {
}

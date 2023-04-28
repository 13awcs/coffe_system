package com.coffe_management_system.service.employee;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.EmployeeRequest;
import com.coffe_management_system.dto.employee.EmployeeResponseProjection;
import com.coffe_management_system.entity.employee.EmployeeEntity;
import com.coffe_management_system.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public ServerResponseDto saveEmployee(EmployeeRequest request) {
        Long employeeId = request.getId();
        Long storeId = request.getStoreId();
        EmployeeEntity employee = new EmployeeEntity() ;
        if(employeeId == null) {
            employeeRepository.save(employee.initInstance(request));
        } else {
            Optional<EmployeeEntity> employeeOpt = employeeRepository.findByStoreIdAndId(storeId, employeeId);
            if(employeeOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            employee.setCreateTime(employeeOpt.get().getCreateTime());
            employee.with(request);
            employeeRepository.save(employee);
        }
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto detailEmployee(Long storeId, Long id) {
        EmployeeResponseProjection response = employeeRepository.detailEmployee(storeId, id);
        if(response == null) {
            return ServerResponseDto.with(ResponseCase.NOT_FOUND);
        }
        return ServerResponseDto.success(response);
    }

    public Page<EmployeeResponseProjection> getPageEmployee(Long storeId, Pageable pageable) {
        return employeeRepository.getPageEmployee(storeId, pageable);
    }

    public ServerResponseDto delete(Long id) {
        employeeRepository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }
}

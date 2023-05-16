package com.coffe_management_system.service.employee;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.EmployeeRequest;
import com.coffe_management_system.dto.employee.EmployeeResponseProjection;
import com.coffe_management_system.entity.employee.EmployeeEntity;
import com.coffe_management_system.entity.store.StoreEntity;
import com.coffe_management_system.repository.employee.EmployeeRepository;
import com.coffe_management_system.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final StoreRepository storeRepository;

    public ServerResponseDto saveEmployee(EmployeeRequest request) {
        Long employeeId = request.getId();
        Long storeId = request.getStoreId();
        EmployeeEntity employee = new EmployeeEntity() ;
        if(employeeId == null) {
            employee.setName(request.getName());
            employee.setDob(request.getDob());
            employee.setAddress(request.getAddress());
            employee.setPhone(request.getPhone());
            employee.setEmail(request.getEmail());
            employee.setStoreId(request.getStoreId());
            employee.setShiftId(request.getShiftId());
            employee.setCreateTime(new Date());
        } else {
            Optional<EmployeeEntity> employeeOpt = employeeRepository.findByStoreIdAndId(storeId, employeeId);
            if(employeeOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            employee.setId(request.getId());
            employee.setName(request.getName());
            employee.setDob(request.getDob());
            employee.setAddress(request.getAddress());
            employee.setPhone(request.getPhone());
            employee.setEmail(request.getEmail());
            employee.setStoreId(request.getStoreId());
            employee.setShiftId(request.getShiftId());
            employee.setCreateTime(employeeOpt.get().getCreateTime());
        }
        employeeRepository.save(employee);
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

    public ServerResponseDto getStore(Long employeeId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        Optional<StoreEntity> store = storeRepository.findById(employee.get().getStoreId());
        if (store.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        List<StoreEntity> list = List.of(store.get());
        return ServerResponseDto.success(list);
    }
}

package com.coffe_management_system.service.customer;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.customer.CustomerRequest;
import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.repository.customer.TypeCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final TypeCustomerService typeCustomerService;

    @Transactional
    public ServerResponseDto saveCustomer(CustomerRequest request) {
        Long id = request.getId();
        CustomerEntity entity = new CustomerEntity();
        if(id == null) {
            CustomerEntity fromDB = repository.save(entity.initInstance(request));
            typeCustomerService.saveTypeCustomer(fromDB.getId());
        } else {
            Optional<CustomerEntity> optional = repository.findById(id);
            if(optional.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            entity.setId(request.getId());
            entity.setName(request.getName());
            entity.setPhone(request.getPhone());
            entity.setPoint(optional.get().getPoint());
            entity.setCreateTime(optional.get().getCreateTime());
            repository.save(entity);
        }
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto delete(Long id) {
        repository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }

}

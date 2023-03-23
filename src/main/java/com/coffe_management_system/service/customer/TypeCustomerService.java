package com.coffe_management_system.service.customer;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.entity.customer.TypeCustomer;
import com.coffe_management_system.entity.customer.TypeCustomerEntity;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.repository.customer.TypeCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeCustomerService {
    private final TypeCustomerRepository repository;
    private final CustomerRepository customerRepository;

    public ServerResponseDto saveTypeCustomer(Long customerId) {
        CustomerEntity customer = customerRepository.findCustomerById(customerId);
        TypeCustomer typeCustomer = setTypeCustomer(customer);
        TypeCustomerEntity entity = new TypeCustomerEntity();
        entity.setCustomerId(customerId);
        entity.setTypeCustomer(typeCustomer);
        repository.save(entity);
        return ServerResponseDto.SUCCESS;
    }

    public TypeCustomer setTypeCustomer(CustomerEntity entity) {
        if(entity.getPoint() >= 30 && entity.getPoint() <= 49) {
            return TypeCustomer.SILVER;
        }else if(entity.getPoint() >= 50 && entity.getPoint() <= 79) {
            return  TypeCustomer.GOLD;
        } else if(entity.getPoint() >= 80) {
            return  TypeCustomer.PLATINUM;
        }
        return TypeCustomer.NORMAL;
    }
}

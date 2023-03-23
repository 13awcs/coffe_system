package com.coffe_management_system.repository.customer;

import com.coffe_management_system.entity.customer.TypeCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeCustomerRepository extends JpaRepository<TypeCustomerEntity, Long> {

    @Query(value = "select t from TypeCustomerEntity t where t.customerId = ?1")
    TypeCustomerEntity getDiscountByCustomerId(Long customerId);
}

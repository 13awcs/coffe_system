package com.coffe_management_system.repository.customer;

import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.entity.order.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "select c from CustomerEntity c where c.id = ?1")
    CustomerEntity findCustomerById(Long id);
}

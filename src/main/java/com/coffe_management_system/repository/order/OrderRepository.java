package com.coffe_management_system.repository.order;

import com.coffe_management_system.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select o from OrderEntity o where o.tableId = ?1 and o.isPaid = false")
    Optional<OrderEntity> findByTableIdAndIsPaidFalse(Long tableId);

    @Query(value = "select o from OrderEntity o where o.status = ?1")
    List<OrderEntity> findByStatus(boolean status);
}

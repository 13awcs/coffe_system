package com.coffe_management_system.repository.order;

import com.coffe_management_system.dto.table.TableResponseProjection;
import com.coffe_management_system.entity.order.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    @Query(value = "select t.id as id, t.name as name, t.status as isStatus from TableEntity t " +
            "join OrderEntity o on t.id = o.tableId " +
            "where o.id = ?1 and t.status = ?2 ")
    TableResponseProjection findByOrderIdAndStatusIsFalse(Long orderId, boolean status);
}

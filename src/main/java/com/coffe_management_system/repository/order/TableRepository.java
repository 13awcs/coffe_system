package com.coffe_management_system.repository.order;

import com.coffe_management_system.dto.table.TableResponseProjection;
import com.coffe_management_system.entity.order.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Long> {

    Optional<TableEntity> findByStoreIdAndId(Long storeId, Long tableId);

    @Query(value = "select t.id as id, t.name as name, t.status as isStatus from TableEntity t " +
            "join OrderEntity o on t.id = o.tableId " +
            "where o.storeId = ?1 and o.id = ?2 and t.status = ?3 ")
    TableResponseProjection findByStoreIdAndOrderIdAndStatusIsFalse(Long storeId, Long orderId, boolean status);

    List<TableEntity> findByStoreId(Long storeId);
}

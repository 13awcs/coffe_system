package com.coffe_management_system.repository.order;

import com.coffe_management_system.dto.order.ItemResponseForOrder;
import com.coffe_management_system.dto.statistic.date.StatisticSoldItemDto;
import com.coffe_management_system.entity.order_item.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query(value = "select oi.id as id, oi.order_id as orderId, oi.item_id as itemId, i.name as name, oi.quantity as quantity, " +
            "i.price as price " +
            "from order_item oi " +
            "join item i on oi.item_id = i.id " +
            "where oi.order_id = ?1", nativeQuery = true)
    List<ItemResponseForOrder> findByOrderId(Long orderId);

    @Modifying
    void deleteByOrderId(Long orderId);

    @Query(value = "select sum(oi.unit_price) from order_item oi where oi.order_id = ?1", nativeQuery = true)
    Double getPriceByOrderId(Long orderId);

    @Query(value = "select oi.id as id, oi.order_id as orderId, oi.item_id as itemId, oi.quantity as quantity " +
            "from order_item oi " +
            "where oi.order_id in ?1", nativeQuery = true)
    List<ItemResponseForOrder> findByOrderIdIn(Collection<Long> listOrderId);

    @Query(value = "select sum(oi.unit_price) from order_item oi where oi.order_id = ?1 ", nativeQuery = true)
    Integer getFinalPrice(Long orderId);

    @Query(value = "select oi.item_id as id, sum(oi.quantity) as quantity " +
            "from order_item oi join o_order o " +
            "on oi.order_id = o.id " +
            "where DATE_FORMAT(o.create_time, '%Y/%m/%d') = ?2 and o.store_id = ?1 " +
            "group by oi.item_id ", nativeQuery = true)
    List<StatisticSoldItemDto> statisticSoldItemByDate(Long storeId, String date);

    @Query(value = "select max(o.id) " +
            "from order_item oi " +
            "join o_order o on oi.order_id = o.id " +
            "join t_table t on o.table_id = t.id " +
            "where t.id = ?1", nativeQuery = true)
    Long getMaxOrderIdByTableId(Long tableId);
}

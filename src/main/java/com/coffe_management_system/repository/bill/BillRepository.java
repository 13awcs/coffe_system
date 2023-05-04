package com.coffe_management_system.repository.bill;

import com.coffe_management_system.entity.order.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

    @Query(value = "select b from BillEntity b where b.id = ?1")
    BillEntity findBillById(Long id);

    @Query(value = "select * from bill b " +
            "where DATE_FORMAT(b.create_time, '%Y/%m/%d') = ?2 and b.store_id = ?1 " +
            "order by b.create_time desc ", nativeQuery = true)
    List<BillEntity> getListBillByDate(Long storeId, String date);

    @Query(value = "select count(b.id) from bill b where DATE_FORMAT(b.create_time, '%Y/%m/%d') = ?2 and b.store_id = ?1 ", nativeQuery = true)
    int countBillByDate(Long storeId, String date);

    @Query(value = "select sum(b.final_price) from bill b where DATE_FORMAT(b.create_time, '%Y/%m/%d') = ?2 and b.store_id = ?1 ", nativeQuery = true)
    Double getRevenueByDate(Long storeId, String date);
}

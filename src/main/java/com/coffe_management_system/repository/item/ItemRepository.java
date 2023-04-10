package com.coffe_management_system.repository.item;

import com.coffe_management_system.dto.item.ItemInCategoryResponse;
import com.coffe_management_system.dto.item.ItemResponse;
import com.coffe_management_system.dto.item.ItemResponseProjection;
import com.coffe_management_system.entity.item.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByStoreIdAndId(Long storeId, Long itemId);

    @Query(value = "select i.id, i.name, i.category_id, i.image, i.price from item i" +
            "where i.store_id = ?1",nativeQuery = true)
    Page<ItemResponseProjection> getPageItems(Long storeId, Pageable pageable);

    @Query(value = "select i.id, i.name, i.image, i.price from item i " +
            "where i.category_id = ?2 and i.store_id = 1",nativeQuery = true)
    Page<ItemInCategoryResponse> getPageItemsByCategoryId(Long storeId, Long categoryId, Pageable pageable);

    @Query(value = "select i.id, i.name, i.image, i.price from item i where i.category_id = :id",nativeQuery = true)
    List<ItemInCategoryResponse> getAll (Long id);
}

package com.coffe_management_system.repository.item;

import com.coffe_management_system.entity.item.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
}

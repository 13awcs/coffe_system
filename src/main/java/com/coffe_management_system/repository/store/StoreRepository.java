package com.coffe_management_system.repository.store;

import com.coffe_management_system.entity.store.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}

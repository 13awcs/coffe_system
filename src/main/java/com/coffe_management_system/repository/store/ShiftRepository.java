package com.coffe_management_system.repository.store;

import com.coffe_management_system.entity.store.ShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long> {
}

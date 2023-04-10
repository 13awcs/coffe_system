package com.coffe_management_system.repository.employee;

import com.coffe_management_system.dto.employee.EmployeeResponseProjection;
import com.coffe_management_system.entity.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByStoreIdAndId(Long storeId, Long employeeId);

    @Query(value = "select em.id as id, em.name as name, em.dob as dob, em.address as address, em.phone as phone, em.email as email, " +
            "st.name as store, sh.name as shift " +
            "from employee em " +
            "join store st on em.store_id = st.id " +
            "join shift sh on em.shift_id = sh.id " +
            "where em.store_id = ?1 and em.id = ?2 ", nativeQuery = true)
    EmployeeResponseProjection detailEmployee(Long storeId, Long id);
}

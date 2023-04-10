package com.coffe_management_system.entity.employee;

import com.coffe_management_system.dto.employee.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dob;
    private String address;
    private String phone;
    private String email;
    private Long storeId;
    private Long shiftId;

    public EmployeeEntity initInstance(Long storeId, EmployeeRequest request) {
        return EmployeeEntity.builder()
                .name(request.getName())
                .dob(request.getDob())
                .address(request.getAddress())
                .phone(request.getPhone())
                .email(request.getEmail())
                .storeId(storeId)
                .shiftId(request.getShiftId())
                .build();
    }

    public EmployeeEntity with(Long storeId, EmployeeRequest request) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setDob(request.getDob());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setStoreId(storeId);
        entity.setShiftId(request.getShiftId());
        return entity;
    }
}

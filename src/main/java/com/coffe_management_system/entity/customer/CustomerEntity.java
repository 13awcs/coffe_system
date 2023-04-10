package com.coffe_management_system.entity.customer;

import com.coffe_management_system.dto.customer.CustomerRequest;
import com.coffe_management_system.dto.item.ItemRequest;
import com.coffe_management_system.entity.item.ItemEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private int point;
    private Date createTime;

    public static CustomerEntity initInstance(CustomerRequest request) {
        return CustomerEntity.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .point(request.getPoint())
                .createTime(new Date())
                .build();
    }

    public CustomerEntity with(CustomerRequest request) {
        return CustomerEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .phone(request.getPhone())
                .build();
    }

}

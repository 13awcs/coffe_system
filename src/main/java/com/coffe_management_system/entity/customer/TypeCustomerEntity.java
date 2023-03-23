package com.coffe_management_system.entity.customer;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "type_customer")
public class TypeCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TypeCustomer.Converter.class)
    private TypeCustomer typeCustomer;

    private Long customerId;
}

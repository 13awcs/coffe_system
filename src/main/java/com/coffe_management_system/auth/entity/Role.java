package com.coffe_management_system.auth.entity;

import javax.persistence.*;

@Entity
public class Role {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_WAITER = "ROLE_WAITER";
    public static final String ROLE_CASHIER = "ROLE_CASHIER";
    public static final String ROLE_BARISTA = "ROLE_BARISTA";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypePermission permissionRole;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

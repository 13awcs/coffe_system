package com.coffe_management_system.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Long employeeId;

    private String role;

    public User() {}

//    public User(String name, String username, String password, Collection<Role> roles) {
//        this.name = name;
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//    }


    public User(String username, String password, Long employeeId, String role) {
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.role = role;
    }
}

package com.coffe_management_system.auth.service;

import com.coffe_management_system.auth.entity.Role;
import com.coffe_management_system.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    //todo: use constructor injection with lombok
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

}

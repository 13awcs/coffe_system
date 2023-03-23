package com.coffe_management_system.auth.service;

import com.coffe_management_system.auth.entity.Role;
import com.coffe_management_system.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    //todo: use constructor injection with lombok
    @Autowired
    private RoleRepository roleRepository;

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

}

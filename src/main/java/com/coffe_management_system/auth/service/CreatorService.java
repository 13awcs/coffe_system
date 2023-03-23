package com.coffe_management_system.auth.service;

import com.coffe_management_system.auth.dto.RegistrationDto;
import com.coffe_management_system.auth.entity.Role;
import com.coffe_management_system.auth.entity.User;
import com.coffe_management_system.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreatorService { //todo: move to UserService
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    //todo: remove this
    private final RoleService roleService;

    public final User save(User user) {
        return userRepository.save(user);
    }

    public User create(RegistrationDto request) {//todo: throw what? need to handle this
        User user = new User(request.username(), passwordEncoder.encode(request.password()), request.employeeId(), request.role());
        Role role = new Role(request.role());
        roleService.save(role);
        return save(user); //todo: what will occur if 2 user have same name
    }

//    public User create(RegistrationDto request) {
//        List<Role> roles = new ArrayList<>();
//        try {
//            roles.add(roleService.getByName(Role.ROLE_ADMIN));
//        } catch (Exception ex) {}
//        return create(request, roles);
//    }
}

package com.coffe_management_system.auth.service;

import com.coffe_management_system.auth.dto.RegistrationDto;
import com.coffe_management_system.auth.entity.Role;
import com.coffe_management_system.auth.entity.User;
import com.coffe_management_system.auth.repository.UserRepository;
import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  RoleService roleService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role -> {
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//            });
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }



    public ServerResponseDto create(RegistrationDto request) {
        User userFromDB = userRepository.findByUsername(request.username());
        if(userFromDB != null) {
            return ServerResponseDto.with(ResponseCase.USERNAME_IS_USED);
        }
        User user = new User(request.username(), passwordEncoder.encode(request.password()), request.employeeId(), request.role());
        Role role = new Role(request.role());
        roleService.save(role);
        save(user);
        return ServerResponseDto.SUCCESS;
    }

}

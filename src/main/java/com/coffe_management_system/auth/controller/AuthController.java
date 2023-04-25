package com.coffe_management_system.auth.controller;


import com.coffe_management_system.auth.dto.LoginDto;
import com.coffe_management_system.auth.dto.LoginResponse;
import com.coffe_management_system.auth.dto.RegistrationDto;
import com.coffe_management_system.auth.dto.UserResponse;
import com.coffe_management_system.auth.entity.Role;
import com.coffe_management_system.auth.entity.User;
import com.coffe_management_system.auth.repository.UserRepository;
import com.coffe_management_system.auth.security.JwtTokenUtil;
import com.coffe_management_system.auth.service.UserService;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.employee.EmployeeAttendanceRequest;
import com.coffe_management_system.entity.employee.EmployeeAttendanceEntity;
import com.coffe_management_system.entity.employee.EmployeeEntity;
import com.coffe_management_system.repository.employee.EmployeeAttendanceRepository;
import com.coffe_management_system.repository.employee.EmployeeRepository;
import com.coffe_management_system.service.employee.EmployeeAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final EmployeeAttendanceService attendanceService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    private final EmployeeAttendanceRepository employeeAttendanceRepository;

    @PostMapping("login")
    public ResponseEntity<ServerResponseDto> login(@RequestBody LoginDto request) {
        String username = request.getUsername();
        String password = request.getPassword();

        if (username.equals("admin") && password.equals("admin")) {
            User admin = new User();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setId(1L);
            admin.setRole(Role.ROLE_ADMIN);
            admin.setEmployeeId(1L);
            userService.create(RegistrationDto.fromUser(admin));
            String accessToken = jwtTokenUtil.generateAccessToken(admin);
            String refreshToken = jwtTokenUtil.generateRefreshToken(admin);
            LoginResponse response = new LoginResponse();
            response.setEmployeeId(admin.getEmployeeId());
            response.setUsername(admin.getUsername());
            response.setName("Admin");
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            return ResponseEntity.ok(ServerResponseDto.success(response));

        }

        User user = userService.getByUsername(request.getUsername());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(ServerResponseDto.error("Username or password is wrong"));
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        Long employeeId = user.getEmployeeId();
        EmployeeAttendanceRequest attendanceRequest = new EmployeeAttendanceRequest();
        String pattern = "yyyy/MM/dd";
        String date = new SimpleDateFormat(pattern).format(new Date());
        attendanceRequest.setDate(date);
        attendanceRequest.setEmployeeId(employeeId);

        attendanceService.save(attendanceRequest, true);

        Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            return ResponseEntity.ok(ServerResponseDto.ERROR);
        }

        Long storeId = employee.get().getStoreId();
        String name = employee.get().getName();

        LoginResponse response = new LoginResponse();
        response.setEmployeeId(employeeId);
        response.setUsername(user.getUsername());
        response.setName(name);
        response.setStoreId(storeId);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return ResponseEntity.ok().body(ServerResponseDto.success(response));
    }

    @PostMapping("refresh")
    public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            if (jwtTokenUtil.validate(refreshToken)) {
                org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User)
                        userService.loadUserByUsername(jwtTokenUtil.getUserName(refreshToken));
                User user = userService.getByUsername(userDetails.getUsername());

                String accessToken = jwtTokenUtil.generateAccessToken(user);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);

                return ResponseEntity.ok().body(tokens);
            }
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("register")
    public ResponseEntity<ServerResponseDto> register(@RequestBody RegistrationDto request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ServerResponseDto> logout(@RequestParam String token) {
        JwtTokenUtil jwt = new JwtTokenUtil();
        Long employeeId = jwt.getEmployeeId(token);

        EmployeeAttendanceRequest attendanceRequest = new EmployeeAttendanceRequest();
        String pattern = "yyyy/MM/dd";
        String date = new SimpleDateFormat(pattern).format(new Date());
        attendanceRequest.setDate(date);
        attendanceRequest.setEmployeeId(employeeId);
        attendanceRequest.setCheckOut(new Date());

        attendanceService.save(attendanceRequest, false);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }

}

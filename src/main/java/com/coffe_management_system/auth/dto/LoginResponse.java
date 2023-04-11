package com.coffe_management_system.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {
    private Long employeeId;
    private Long storeId;
    private String username;
    private String name;
    private String accessToken;
    private String refreshToken;
}

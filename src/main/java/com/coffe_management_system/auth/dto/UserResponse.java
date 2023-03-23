package com.coffe_management_system.auth.dto;

import com.coffe_management_system.auth.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Setter
@Getter
public class UserResponse { //todo: split to other package for clean architecture
    private String username;
    private String name;

    public static UserResponse fromEntity(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}

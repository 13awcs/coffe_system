package com.coffe_management_system.auth.dto;

import java.util.Objects;

public final class RegistrationDto {
    private final String username;
    private final String password;
    private final Long employeeId;
    private final String role;

    RegistrationDto(String username, String password, Long employeeId, String role) {
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.role = role;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public Long employeeId() {
        return employeeId;
    }

    public String role() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RegistrationDto) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.employeeId, that.employeeId) &&
                Objects.equals(this.role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, employeeId, role);
    }

    @Override
    public String toString() {
        return "RegistrationDto[" +
                "username=" + username + ", " +
                "password=" + password + ", " +
                "employeeId=" + employeeId + ", " +
                "role=" + role + ']';
    }

}

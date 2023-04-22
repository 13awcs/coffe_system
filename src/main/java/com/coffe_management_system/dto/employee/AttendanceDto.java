package com.coffe_management_system.dto.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AttendanceDto {
    private String name;
    private String shift;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String date;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date checkin;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date checkout;
}

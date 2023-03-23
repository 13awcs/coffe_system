package com.coffe_management_system.entity.store;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "shift")
public class ShiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Time startTime;
    private Time endTime;
    private Integer moneyPerHour;
}

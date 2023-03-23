package com.coffe_management_system.dto.statistic.date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class StatisticOrderByDateDto {
    private int quantityOrder;
    private Double revenue;
}

package com.coffe_management_system.service.statistic.date;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.statistic.date.StatisticOrderByDateDto;
import com.coffe_management_system.repository.bill.BillRepository;
import com.coffe_management_system.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StatisticByDateService {
    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;

    public ServerResponseDto statisticOrderByDate(String date) {
        int quantityOrder = billRepository.countBillByDate(date);
        System.err.println(quantityOrder);
        Double revenue = billRepository.getRevenueByDate(date);
        System.err.println(revenue);
        StatisticOrderByDateDto result = new StatisticOrderByDateDto();
        result.setQuantityOrder(quantityOrder);
        result.setRevenue(revenue);

        return ServerResponseDto.success(result);
    }

    public ServerResponseDto statisticItemSoldByDate(String date) {
        return ServerResponseDto.success(orderItemRepository.statisticSoldItemByDate(date));
    }
}

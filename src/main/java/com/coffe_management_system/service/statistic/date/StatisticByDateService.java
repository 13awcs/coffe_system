package com.coffe_management_system.service.statistic.date;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.statistic.date.StatisticOrderByDateDto;
import com.coffe_management_system.repository.bill.BillRepository;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class StatisticByDateService {
    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    public ServerResponseDto statisticOrderByDate(String date) {
        int quantityOrder = billRepository.countBillByDate(date);
        Double revenue = billRepository.getRevenueByDate(date);
        StatisticOrderByDateDto result = new StatisticOrderByDateDto();
        result.setQuantityOrder(quantityOrder);
        result.setRevenue(revenue);
        return ServerResponseDto.success(result);
    }

    public ServerResponseDto statisticItemSoldByDate(String date) {
        return ServerResponseDto.success(orderItemRepository.statisticSoldItemByDate(date));
    }

    public ServerResponseDto statisticNewCustomerByDate(String date) {
        return ServerResponseDto.success(customerRepository.countNumberNewCustomerByDate(date));
    }

    public ServerResponseDto statisticPerformanceByDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(date));
        cal.add( Calendar.DATE, -1 );
        String yesterday = dateFormat.format(cal.getTime());
        System.out.println("Date increase by one.."+yesterday);
        Double result = billRepository.getRevenueByDate(date) - billRepository.getRevenueByDate(yesterday);
        return ServerResponseDto.success(result);
    }
}

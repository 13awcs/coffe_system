package com.coffe_management_system.controller.statistic;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.service.statistic.date.StatisticByDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/statistic-by-date")
public class DateStatisticController {
    private final StatisticByDateService service;

    @GetMapping("/revenue")
    public ResponseEntity statisticOrderByDate(@RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticOrderByDate(byDate));
    }

    @GetMapping("/sold-item-by-date")
    public ResponseEntity<ServerResponseDto> statisticSoldItemByDate(@RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticItemSoldByDate(byDate));
    }

    @GetMapping("/new-customer")
    public ResponseEntity<ServerResponseDto> statisticNewCustomerByDate(@RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticNewCustomerByDate(byDate));
    }

    @GetMapping("/performance")
    public ResponseEntity<ServerResponseDto> statisticPerformanceByDate(@RequestParam Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticPerformanceByDate(byDate));
    }
}

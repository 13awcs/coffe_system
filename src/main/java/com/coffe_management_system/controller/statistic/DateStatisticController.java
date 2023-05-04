package com.coffe_management_system.controller.statistic;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.service.statistic.date.StatisticByDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("admin/statistic-by-date/{storeId}")
public class DateStatisticController {
    private final StatisticByDateService service;

    @GetMapping("/revenue")
    public ResponseEntity statisticOrderByDate(@PathVariable Long storeId, @RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticOrderByDate(storeId, byDate));
    }

    @GetMapping("/sold-item-by-date")
    public ResponseEntity<ServerResponseDto> statisticSoldItemByDate(@PathVariable Long storeId, @RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticItemSoldByDate(storeId, byDate));
    }

    @GetMapping("/new-customer")
    public ResponseEntity<ServerResponseDto> statisticNewCustomerByDate(@RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticNewCustomerByDate(byDate));
    }

    @GetMapping("/performance")
    public ResponseEntity<ServerResponseDto> statisticPerformanceByDate(@PathVariable Long storeId, @RequestParam Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String byDate = formatter.format(date);
        return ResponseEntity.ok(service.statisticPerformanceByDate(storeId, byDate));
    }
}

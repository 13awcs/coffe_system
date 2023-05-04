package com.coffe_management_system.controller.bill;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.payment.PaymentRequest;
import com.coffe_management_system.service.bill.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("{storeId}/bill")
public class BillController {
    private final BillService billService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveBill(@PathVariable Long storeId,
                                                      @RequestBody PaymentRequest request){
        return ResponseEntity.ok(billService.saveBill(storeId, request));
    }

    @GetMapping("/history")
    public ResponseEntity<ServerResponseDto> history(@PathVariable Long storeId, @RequestParam Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String createTime = formatter.format(date);
        return ResponseEntity.ok(billService.getListBillByDate(storeId, createTime));
    }
}

package com.coffe_management_system.controller.order;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.order.OrderRequest;
import com.coffe_management_system.service.order.OrderItemService;
import com.coffe_management_system.service.order.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("admin/{storeId}/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveOrderItem(@PathVariable Long storeId, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.saveOrder(storeId, request));
    }

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> getAllOrder(@PathVariable Long storeId) {
        return ResponseEntity.ok(ServerResponseDto.success(orderService.getAllOrder()));
    }

    @GetMapping("/list-by-status")
    public ResponseEntity<ServerResponseDto> getOrderByStatus(@PathVariable Long storeId, @RequestParam boolean status) {
        return ResponseEntity.ok(ServerResponseDto.success(orderService.getOrderByStatus(status)));
    }

    @PostMapping("change-status/{orderId}")
    public ResponseEntity<ServerResponseDto> getOrderByStatus(@PathVariable Long storeId, @PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.changeStatusForOrder(orderId));
    }

    @GetMapping("/detail/{tableId}")
    public ResponseEntity<ServerResponseDto> detailOrder(@PathVariable Long storeId, @PathVariable Long tableId) {
        return ResponseEntity.ok(orderService.detailOrderByTableId(tableId));
    }
}

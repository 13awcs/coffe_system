package com.coffe_management_system.controller.order;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.order.OrderRequest;
import com.coffe_management_system.service.order.OrderItemService;
import com.coffe_management_system.service.order.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("admin/order")
public class OrderController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveOrderItem(@RequestBody OrderRequest request, @RequestHeader("Authorization") String token) {
        orderService.saveOrder(request, token);
        return ResponseEntity.ok(orderService.saveOrder(request, token));
    }

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> getAllOrder() {
        return ResponseEntity.ok(ServerResponseDto.success(orderService.getAllOrder()));
    }

    @GetMapping("/list-by-status")
    public ResponseEntity<ServerResponseDto> getOrderByStatus(@RequestParam boolean status) {
        return ResponseEntity.ok(ServerResponseDto.success(orderService.getOrderByStatus(status)));
    }

    @PostMapping("change-status/{orderId}")
    public ResponseEntity<ServerResponseDto> getOrderByStatus(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.changeStatusForOrder(orderId));
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<ServerResponseDto> detailOrder(@PathVariable Long tableId) {
        return ResponseEntity.ok(orderService.detailOrderByTableId(tableId));
    }
}

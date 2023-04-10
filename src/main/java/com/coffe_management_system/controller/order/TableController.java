package com.coffe_management_system.controller.order;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.table.TableRequest;
import com.coffe_management_system.service.order.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/{storeId}/table")
public class TableController {

    private final TableService service;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveTable(@PathVariable Long storeId, @RequestBody TableRequest request) {
        return ResponseEntity.ok(service.saveTable(storeId, request));
    }

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> getListTable(@PathVariable Long storeId) {
        return ResponseEntity.ok(service.getListTable(storeId));
    }
}

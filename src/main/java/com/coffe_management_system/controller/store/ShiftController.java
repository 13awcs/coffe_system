package com.coffe_management_system.controller.store;

import com.coffe_management_system.entity.store.ShiftEntity;
import com.coffe_management_system.repository.store.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("admin/{storeId}/shift")
public class ShiftController {
    private final ShiftRepository shiftRepository;

    @GetMapping("/list")
    public ResponseEntity<List<ShiftEntity>> listShift(@PathVariable Long storeId) {
        return ResponseEntity.ok(shiftRepository.findAll());
    }
}

package com.coffe_management_system.controller.store;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.coffe_management_system.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> listStore() {
        return ResponseEntity.ok(storeService.listStore());
    }
}

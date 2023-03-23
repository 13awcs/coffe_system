package com.coffe_management_system.controller.item;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.item.ItemCategoryRequest;
import com.coffe_management_system.service.item.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCategory(@RequestBody ItemCategoryRequest request) {
        return ResponseEntity.ok(itemCategoryService.saveItemCategory(request));
    }

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> getItemCategories() {
        return ResponseEntity.ok(itemCategoryService.getItemCategories());
    }
}

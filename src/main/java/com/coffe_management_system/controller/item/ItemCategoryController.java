package com.coffe_management_system.controller.item;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.item.ItemCategoryRequest;
import com.coffe_management_system.service.item.ItemCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://coffe-system-web.vercel.app")
@RequestMapping("/category")
public class ItemCategoryController {
    private final ItemCategoryService itemCategoryService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCategory(@RequestBody ItemCategoryRequest request) {
        return ResponseEntity.ok(itemCategoryService.saveItemCategory(request));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ServerResponseDto> detail(@PathVariable Long id) {
        return ResponseEntity.ok(itemCategoryService.detail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServerResponseDto> delete(@PathVariable Long id) {
        itemCategoryService.delete(id);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }

    @GetMapping("/list")
    public ResponseEntity<ServerResponseDto> getItemCategories() {
        return ResponseEntity.ok(itemCategoryService.getItemCategories());
    }
}

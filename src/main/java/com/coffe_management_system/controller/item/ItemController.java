package com.coffe_management_system.controller.item;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.item.ItemInCategoryResponse;
import com.coffe_management_system.dto.item.ItemRequest;
import com.coffe_management_system.dto.item.ItemResponse;
import com.coffe_management_system.dto.item.ItemResponseProjection;
import com.coffe_management_system.entity.item.ItemEntity;
import com.coffe_management_system.service.item.ItemService;
import com.coffe_management_system.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("item/{storeId}")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveItem(@RequestBody ItemRequest request, @PathVariable Long storeId) {
        return ResponseEntity.ok(itemService.saveItem(request, storeId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ItemResponseProjection>> getItems(@RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "20") int size,
                                                                 @RequestParam(defaultValue = "name") String sortField,
                                                                 @RequestParam(defaultValue = "desc") String sortDir,
                                                                 @PathVariable Long storeId) {
        Pageable pageable = PageUtil.getPage(sortDir, sortField, page, size);
        return ResponseEntity.ok(itemService.getPageItems(storeId, pageable));
    }

    @GetMapping("/{categoryId}/list-item")
    public ResponseEntity<Page<ItemInCategoryResponse>> getItemsByCategoryId(@RequestParam(defaultValue = "1") int page,
                                                                             @RequestParam(defaultValue = "20") int size,
                                                                             @RequestParam(defaultValue = "name") String sortField,
                                                                             @RequestParam(defaultValue = "desc") String sortDir,
                                                                             @PathVariable Long storeId,
                                                                             @PathVariable Long categoryId) {
        Pageable pageable = PageUtil.getPage(sortDir, sortField, page, size);
        return ResponseEntity.ok(itemService.getPageItemsByCategoryId(storeId, categoryId,pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServerResponseDto> detailItem (@PathVariable Long storeId, @PathVariable Long id) {
        return ResponseEntity.ok(itemService.detailItem(storeId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServerResponseDto> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }
}

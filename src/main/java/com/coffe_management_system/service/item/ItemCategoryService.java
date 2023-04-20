package com.coffe_management_system.service.item;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.item.ItemCategoryRequest;
import com.coffe_management_system.dto.item.ItemResponse;
import com.coffe_management_system.entity.item.ItemCategoryEntity;
import com.coffe_management_system.repository.item.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemCategoryService {
    private final ItemCategoryRepository itemCategoryRepository;

    @Transactional
    public ServerResponseDto saveItemCategory(ItemCategoryRequest request) {
        Long categoryId = request.getId();
        ItemCategoryEntity category = new ItemCategoryEntity();
        if(categoryId == null) {
            category.setName(request.getName());
            category.setCreateTime(new Date());
            itemCategoryRepository.save(category);
        } else {
            Optional<ItemCategoryEntity> categoryOpt = itemCategoryRepository.findById(request.getId());
            if(categoryOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            category.setId(categoryOpt.get().getId());
            category.setName(request.getName());
            category.setCreateTime(categoryOpt.get().getCreateTime());
        }
        itemCategoryRepository.save(category);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto getItemCategories() {
        return ServerResponseDto.success(itemCategoryRepository.findAll());
    }

    @Transactional
    public ServerResponseDto delete(Long id) {
        itemCategoryRepository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto detail(Long id) {
        return ServerResponseDto.success(itemCategoryRepository.findById(id));
    }
}

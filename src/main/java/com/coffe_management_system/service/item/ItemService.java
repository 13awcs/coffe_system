package com.coffe_management_system.service.item;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.item.ItemInCategoryResponse;
import com.coffe_management_system.dto.item.ItemRequest;
import com.coffe_management_system.dto.item.ItemResponse;
import com.coffe_management_system.dto.item.ItemResponseProjection;
import com.coffe_management_system.entity.item.ItemEntity;
import com.coffe_management_system.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public ServerResponseDto saveItem(ItemRequest request) {
        Long itemId = request.getId();
        ItemEntity item = new ItemEntity();
        if(itemId == null) {
            itemRepository.save(item.initInstance(request));
        } else {
            Optional<ItemEntity> itemOpt = itemRepository.findById(request.getId());
            if(itemOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            item.setId(request.getId());
            item.setName(request.getName());
            item.setCategoryId(request.getCategoryId());
            item.setPrice(request.getPrice());
            item.setCreateTime(itemOpt.get().getCreateTime());
            itemRepository.save(item);
        }
        return ServerResponseDto.SUCCESS;
    }

    public Page<ItemResponseProjection> getPageItems(Pageable pageable) {
        return itemRepository.getPageItems(pageable);
    }

    public Page<ItemInCategoryResponse> getPageItemsByCategoryId(Long categoryId, Pageable pageable) {
        return itemRepository.getPageItemsByCategoryId(categoryId, pageable);
    }

    @Transactional
    public ServerResponseDto deleteItem(Long id) {
        itemRepository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto detailItem(Long id) {
        return itemRepository.findById(id)
                .map(item -> ServerResponseDto.success(ItemResponse.fromEntity(item)))
                .orElse(ServerResponseDto.with(ResponseCase.NOT_FOUND));
    }

}

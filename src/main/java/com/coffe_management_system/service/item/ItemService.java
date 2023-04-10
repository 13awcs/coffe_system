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
    public ServerResponseDto saveItem(ItemRequest request, Long storeId) {
        Long itemId = request.getId();
        ItemEntity item = new ItemEntity();
        if(itemId == null) {
            itemRepository.save(item.initInstance(request, storeId));
        } else {
            Optional<ItemEntity> itemOpt = itemRepository.findById(request.getId());
            if(itemOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            itemRepository.save(item.with(request));
        }
        return ServerResponseDto.SUCCESS;
    }

    public Page<ItemResponseProjection> getPageItems(Long storeId, Pageable pageable) {
        return itemRepository.getPageItems(storeId, pageable);
    }

    public Page<ItemInCategoryResponse> getPageItemsByCategoryId(Long storeId, Long categoryId, Pageable pageable) {
        return itemRepository.getPageItemsByCategoryId(storeId, categoryId, pageable);
    }

    @Transactional
    public ServerResponseDto deleteItem(Long id) {
        itemRepository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto detailItem(Long storeId, Long id) {
        return itemRepository.findByStoreIdAndId(storeId, id)
                .map(item -> ServerResponseDto.success(ItemResponse.fromEntity(storeId, item)))
                .orElse(ServerResponseDto.with(ResponseCase.NOT_FOUND));
    }

}

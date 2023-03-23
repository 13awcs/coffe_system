package com.coffe_management_system.service.order;

import com.coffe_management_system.dto.order.ItemRequestForOrder;
import com.coffe_management_system.entity.order_item.OrderItemEntity;
import com.coffe_management_system.repository.item.ItemRepository;
import com.coffe_management_system.dto.order.ItemResponseForOrder;
import com.coffe_management_system.repository.order.OrderItemRepository;
import com.coffe_management_system.util.StreamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository repository;
    private final ItemRepository itemRepository;

    @Transactional
    public void deleteByOrderId(Long orderId) {
        repository.deleteByOrderId(orderId);
    }

    @Transactional
    public void saveOrderItem(Long orderId, List<ItemRequestForOrder> requests) {
        deleteByOrderId(orderId);

        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (ItemRequestForOrder request : requests ) {
            Integer price = itemRepository.findById(request.getId()).get().getPrice();

            OrderItemEntity entity = new OrderItemEntity();
            entity.setOrderId(orderId);
            entity.setItemId(request.getId());
            entity.setQuantity(request.getQuantity());
            entity.setUnitPrice(price * request.getQuantity());
            orderItemEntities.add(entity);
        }
        repository.saveAll(orderItemEntities);
    }

    public Map<Long, List<ItemResponseForOrder>> getMapListItemByOrderId(List<Long> listOrderId) {
        if(listOrderId.isEmpty()) {
            return Collections.emptyMap();
        }
        List<ItemResponseForOrder> listItemResponse = repository.findByOrderIdIn(listOrderId);
        return StreamUtils.groupingApply(listItemResponse, ItemResponseForOrder::getOrderId);
    }
}

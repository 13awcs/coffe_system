package com.coffe_management_system.service.order;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.order.ItemRequestForOrder;
import com.coffe_management_system.dto.order.OrderRequest;
import com.coffe_management_system.dto.order.OrderResponse;
import com.coffe_management_system.entity.order.OrderEntity;
import com.coffe_management_system.dto.order.ItemResponseForOrder;
import com.coffe_management_system.entity.order.TableEntity;
import com.coffe_management_system.repository.order.OrderItemRepository;
import com.coffe_management_system.repository.order.OrderRepository;
import com.coffe_management_system.repository.order.TableRepository;
import com.coffe_management_system.util.CollectionUtils;
import com.coffe_management_system.util.StreamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;
    private final TableRepository tableRepository;

    @Transactional
    public ServerResponseDto saveOrder(Long storeId, OrderRequest request) {
        List<ItemRequestForOrder> itemRequestForOrders = request.getListItemRequest();
        if (CollectionUtils.isEmpty(itemRequestForOrders)) {
            return ServerResponseDto.error("List itemRequest is empty");
        }
        Long tableId = request.getTableId();
        boolean isCreateNew = request.getId() == null;
        OrderEntity order;

        if (isCreateNew) {
            order = OrderEntity.initInstance(storeId, request);
        } else {
            Optional<OrderEntity> orderOpt = orderRepository.findById(request.getId());
            if (orderOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            order = orderOpt.get();
            order.setEmployeeId(request.getEmployeeId());
            order.setNote(request.getNote());
        }
        orderRepository.save(order);

        orderItemService.saveOrderItem(order.getId(), request.getListItemRequest());

        Optional<TableEntity> tableOpt = tableRepository.findById(tableId);
        if(tableOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        TableEntity table = tableOpt.get();
        if (table.isStatus()) {
            table.setStatus(tableOpt.get().isStatus());
        }
        table.setStatus(!tableOpt.get().isStatus());
        tableRepository.save(table);

        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto detailOrderByTableId(Long tableId) {
        Optional<OrderEntity> order = orderRepository.findByTableIdAndIsPaidFalse(tableId);
        if(order.isEmpty()) {
            return ServerResponseDto.error(ResponseCase.NOT_FOUND);
        }
        List<ItemResponseForOrder> listItem = orderItemRepository.findByOrderId(order.get().getId());
        OrderResponse response = OrderResponse.fromEntity(order.get());
        response.setListItemResponse(listItem);
        return ServerResponseDto.success(response);
    }

    public ServerResponseDto getAllOrder() {
        List<OrderEntity> listOrderFromDB = orderRepository.findAll();

        if(listOrderFromDB.isEmpty()) {
            return ServerResponseDto.with(ResponseCase.EMPTY);
        }
        List<Long> listOrderId =StreamUtils.mapApply(listOrderFromDB,OrderEntity::getId);

        Map<Long, List<ItemResponseForOrder>> mapListItemByOrderId = orderItemService.getMapListItemByOrderId(listOrderId);

        return ServerResponseDto.success(listOrderFromDB.stream()
                .map(entity -> {
                    OrderResponse orderResponse = OrderResponse.fromEntity(entity);
                    orderResponse.setListItemResponse(mapListItemByOrderId.getOrDefault(entity.getId(), Collections.emptyList()));
                    return orderResponse;})
                .collect(Collectors.toList())) ;
    }

    public ServerResponseDto getOrderByStatus(boolean status) {
        List<OrderEntity> listOrderByStatusFromDB = orderRepository.findByStatus(status);

        if(listOrderByStatusFromDB.isEmpty()) {
            return ServerResponseDto.with(ResponseCase.EMPTY);
        }
        List<Long> listOrderId =StreamUtils.mapApply(listOrderByStatusFromDB,OrderEntity::getId);

        Map<Long, List<ItemResponseForOrder>> mapListItemByOrderId = orderItemService.getMapListItemByOrderId(listOrderId);

        return ServerResponseDto.success(listOrderByStatusFromDB.stream()
                .map(entity -> {
                    OrderResponse orderResponse = OrderResponse.fromEntity(entity);
                    orderResponse.setListItemResponse(mapListItemByOrderId.getOrDefault(entity.getId(), Collections.emptyList()));
                    return orderResponse;})
                .collect(Collectors.toList())) ;
    }

    public ServerResponseDto changeStatusForOrder(Long orderId) {
        Optional<OrderEntity> optional = orderRepository.findById(orderId);
        if(optional.isEmpty()) {
            return ServerResponseDto.error(ResponseCase.NOT_FOUND);
        }
        OrderEntity entity = fromOptional(optional);
        entity.setStatus(!entity.isStatus());
        orderRepository.save(entity);
        return ServerResponseDto.SUCCESS;
    }

    OrderEntity fromOptional( Optional<OrderEntity> optional) {
        OrderEntity entity = new OrderEntity();
        entity.setId(optional.get().getId());
        entity.setNote(optional.get().getNote());
        entity.setTableId(optional.get().getTableId());
        entity.setEmployeeId(optional.get().getEmployeeId());
        entity.setCreateTime(optional.get().getCreateTime());
        entity.setStatus(optional.get().isStatus());
        return entity;
    }

}

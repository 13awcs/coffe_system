package com.coffe_management_system.service.store;

import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository repository;

    public ServerResponseDto listStore() {
        return ServerResponseDto.success(repository.findAll());
    }
}

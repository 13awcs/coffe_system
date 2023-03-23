package com.coffe_management_system.service.order;

import com.coffe_management_system.dto.ResponseCase;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.table.TableRequest;
import com.coffe_management_system.entity.order.TableEntity;
import com.coffe_management_system.repository.order.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository repository;

    public ServerResponseDto saveTable(TableRequest request) {
        Long id = request.getId();
        TableEntity entity = new TableEntity();
        if(id == null) {
            entity.setName(request.getName());
        } else {
            Optional<TableEntity> opt = repository.findById(request.getId());
            if(opt.isEmpty()) {
                return ServerResponseDto.error(ResponseCase.NOT_FOUND);
            }
            entity.setId(opt.get().getId());
            entity.setName(opt.get().getName());
        }
        repository.save(entity);
        return ServerResponseDto.SUCCESS;
    }
}

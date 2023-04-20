package com.coffe_management_system.entity.item;

import com.coffe_management_system.dto.item.ItemRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long categoryId;
    private Integer price;
    private Date createTime;

    public ItemEntity initInstance(ItemRequest request) {
        return ItemEntity.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .price(request.getPrice())
                .createTime(new Date())
                .build();
    }

    public ItemEntity with(ItemRequest request) {
        ItemEntity entity = new ItemEntity();
                entity.setId(request.getId());
                entity.setName(request.getName());
                entity.setCategoryId(request.getCategoryId());
                entity.setPrice(request.getPrice());
                return entity;
    }
}

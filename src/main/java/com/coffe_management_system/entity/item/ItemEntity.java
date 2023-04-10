package com.coffe_management_system.entity.item;

import com.coffe_management_system.dto.item.ItemRequest;
import lombok.*;

import javax.persistence.*;

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
    private String image;
    private Integer price;
    private Long storeId;

    public ItemEntity initInstance(ItemRequest request, Long storeId) {
        return ItemEntity.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .image(request.getImage())
                .price(request.getPrice())
                .storeId(storeId)
                .build();
    }

    public ItemEntity with(ItemRequest request) {
        ItemEntity entity = new ItemEntity();
                entity.setId(request.getId());
                entity.setName(request.getName());
                entity.setCategoryId(request.getCategoryId());
                entity.setImage(request.getImage());
                entity.setPrice(request.getPrice());
                return entity;
    }
}

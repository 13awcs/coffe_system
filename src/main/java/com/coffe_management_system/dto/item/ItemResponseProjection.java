package com.coffe_management_system.dto.item;

import java.util.Date;

public interface ItemResponseProjection {
    Long getId();
    String getName();
    Long getCategoryId();
    String getCategoryName();
    Integer getPrice();
    Date getCreateTime();
}

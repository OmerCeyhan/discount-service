package com.omerceyhan.discountservice.dto;

import com.omerceyhan.discountservice.entity.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private String name;
    private ItemType itemType;
    private BigDecimal price;

}

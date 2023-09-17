package com.omerceyhan.discountservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "discount_percentage")
@Getter
@Setter
public class DiscountPercentage {

    public DiscountPercentage(StatusType statusType, String description, BigDecimal discountPercentageValue) {
        this.statusType = statusType;
        this.description = description;
        this.discountPercentageValue = discountPercentageValue;
    }

    @Id
    private StatusType statusType;
    private String description;
    private BigDecimal discountPercentageValue;
}

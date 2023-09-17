package com.omerceyhan.discountservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Document(collection = "discount_fixed")
public class DiscountFixed extends BaseEntity {

    public DiscountFixed(Long id, String description, BigDecimal targetAmount, BigDecimal rewardAmount) {
        super(id);
        this.description = description;
        this.targetAmount = targetAmount;
        this.rewardAmount = rewardAmount;
    }

    @Transient
    public static final String SEQUENCE_NAME = "discount_fixed_sequence";
    private String description;
    private BigDecimal targetAmount;
    private BigDecimal rewardAmount;
}

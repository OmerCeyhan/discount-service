package com.omerceyhan.discountservice.service.discount.percentage;

import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.StatusType;

public interface DiscountPercentageService {
    DiscountPercentage findById(StatusType statusType);
}

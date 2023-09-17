package com.omerceyhan.discountservice.service.discount;

import com.omerceyhan.discountservice.dto.DiscountResponse;
import com.omerceyhan.discountservice.dto.OrderDto;
import com.omerceyhan.discountservice.entity.DiscountFixed;
import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.ItemType;

import java.math.BigDecimal;

public interface DiscountService {
    DiscountResponse calculateDiscount(OrderDto orderDto);

    BigDecimal calculatePercentageDiscount(BigDecimal totalOrderAmount, DiscountPercentage discountPercentage);

    DiscountPercentage findMostProfitablePercentageDiscountForUser(Long userId);

    BigDecimal calculateFixedDiscount(BigDecimal totalOrderAmount, DiscountFixed discountFixed);

    DiscountFixed findMostProfitableFixedDiscountForUser();

    BigDecimal calculateProfitRate(DiscountFixed discount);

    BigDecimal calculateTotalOrderAmount(OrderDto orderDto);

    BigDecimal calculateTotalOrderAmount(OrderDto orderDto, ItemType filtered);
}

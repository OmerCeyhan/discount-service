package com.omerceyhan.discountservice.service.discount;

import com.omerceyhan.discountservice.dto.DiscountResponse;
import com.omerceyhan.discountservice.dto.ItemDto;
import com.omerceyhan.discountservice.dto.OrderDto;
import com.omerceyhan.discountservice.entity.DiscountFixed;
import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.ItemType;
import com.omerceyhan.discountservice.entity.User;
import com.omerceyhan.discountservice.service.discount.fixed.DiscountFixedService;
import com.omerceyhan.discountservice.service.discount.percentage.DiscountPercentageService;
import com.omerceyhan.discountservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountFixedService discountFixedService;
    private final DiscountPercentageService discountPercentageService;
    private final UserService userService;

    public DiscountResponse calculateDiscount(OrderDto orderDto) {
        BigDecimal totalOrderAmount = calculateTotalOrderAmount(orderDto);
        DiscountFixed discountFixed = findMostProfitableFixedDiscountForUser();
        BigDecimal fixedDiscountAmount = calculateFixedDiscount(totalOrderAmount, discountFixed);
        DiscountPercentage discountPercentage = findMostProfitablePercentageDiscountForUser(orderDto.getUserId());
        BigDecimal totalOrderAmountWithoutGroceries = calculateTotalOrderAmount(orderDto, ItemType.GROCERY);
        BigDecimal percentageDiscountAmount = calculatePercentageDiscount(totalOrderAmountWithoutGroceries, discountPercentage);
        BigDecimal totalDiscountAmount = fixedDiscountAmount.add(percentageDiscountAmount);
        BigDecimal netAmount = totalOrderAmount.subtract(totalDiscountAmount);

        return new DiscountResponse(totalOrderAmount, discountFixed.getDescription(), fixedDiscountAmount, discountPercentage.getDescription(), percentageDiscountAmount, netAmount);
    }

    public BigDecimal calculatePercentageDiscount(BigDecimal totalOrderAmount, DiscountPercentage discountPercentage) {
        return totalOrderAmount.multiply(discountPercentage.getDiscountPercentageValue().divide(new BigDecimal("100")));
    }

    public DiscountPercentage findMostProfitablePercentageDiscountForUser(Long userId) {
        User user = userService.findUserById(userId);
        return user.getUserStatuses().stream().map(userStatus -> discountPercentageService.findById(userStatus.getStatusType()))
                .max(Comparator.comparing(DiscountPercentage::getDiscountPercentageValue)).orElse(null);
    }

    public BigDecimal calculateFixedDiscount(BigDecimal totalOrderAmount, DiscountFixed discountFixed) {
        return totalOrderAmount.divide(discountFixed.getTargetAmount(), RoundingMode.FLOOR).multiply(discountFixed.getRewardAmount());
    }

    public DiscountFixed findMostProfitableFixedDiscountForUser() {
        final List<DiscountFixed> all = discountFixedService.findAll();
        return all.stream()
                .max(Comparator.comparing(this::calculateProfitRate))
                .orElse(null);
    }

    public BigDecimal calculateProfitRate(DiscountFixed discount) {
        return discount.getRewardAmount().divide(discount.getTargetAmount());
    }

    public BigDecimal calculateTotalOrderAmount(OrderDto orderDto) {
        return orderDto.getItemList().stream()
                .map(ItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalOrderAmount(OrderDto orderDto, ItemType filtered) {
        return orderDto.getItemList().stream()
                .filter(itemDto -> itemDto.getItemType() != filtered)
                .map(ItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

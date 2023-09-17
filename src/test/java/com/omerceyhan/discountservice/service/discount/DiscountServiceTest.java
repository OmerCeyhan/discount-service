package com.omerceyhan.discountservice.service.discount;

import com.omerceyhan.discountservice.dto.DiscountResponse;
import com.omerceyhan.discountservice.dto.ItemDto;
import com.omerceyhan.discountservice.dto.OrderDto;
import com.omerceyhan.discountservice.service.discount.fixed.DiscountFixedService;
import com.omerceyhan.discountservice.service.discount.percentage.DiscountPercentageService;
import com.omerceyhan.discountservice.service.user.UserService;
import com.omerceyhan.discountservice.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {
    @Mock
    private DiscountFixedService discountFixedService;
    @Mock
    private DiscountPercentageService discountPercentageService;
    @Mock
    private UserService userService;
    @InjectMocks
    private DiscountServiceImpl discountService;

    DiscountFixed discountFixed = new DiscountFixed(1L, "$5 Discount for every $100 spent", new BigDecimal("100"), new BigDecimal("5"));
    DiscountPercentage discountPercentage = new DiscountPercentage(StatusType.EMPLOYEE, "Employee Discount - 30% Discount", new BigDecimal(30));
    UserStatus userStatus = new UserStatus(1L, StatusType.EMPLOYEE);
    User user = new User(1L, "Omer Ceyhan", List.of(userStatus));
    List<ItemDto> itemList = Arrays.asList(
            new ItemDto("Item1", ItemType.GROCERY, new BigDecimal("100")),
            new ItemDto("Item2", ItemType.ELECTRONIC, new BigDecimal("200")),
            new ItemDto("Item3", ItemType.HOME, new BigDecimal("50"))
    );


    @Test
    void testCalculateDiscount() {
        when(discountFixedService.findAll()).thenReturn(List.of(discountFixed));
        when(discountPercentageService.findById(any())).thenReturn(discountPercentage);
        when(userService.findUserById(any())).thenReturn(user);

        OrderDto orderDto = new OrderDto(1L, itemList);
        DiscountResponse result = discountService.calculateDiscount(orderDto);
        assertNotNull(result);
        assertEquals(new BigDecimal("350"), result.getTotalOrderAmount());
        assertEquals("$5 Discount for every $100 spent", result.getFixedDiscountDescription());
        assertEquals(new BigDecimal("15"), result.getFixedDiscountAmount());
        assertEquals("Employee Discount - 30% Discount", result.getPercentageDiscountDescription());
        assertEquals(new BigDecimal("75.0"), result.getPercentageDiscountAmount());
        assertEquals(new BigDecimal("260.0"), result.getNetAmount());
    }

    @Test
    void calculatePercentageDiscountTest() {
        assertEquals(new BigDecimal("30.0"), discountService.calculatePercentageDiscount(new BigDecimal(100), discountPercentage));
    }

    @Test
    void findMostProfitablePercentageDiscountForUserTest() {
        when(userService.findUserById(any())).thenReturn(user);
        when(discountPercentageService.findById(any())).thenReturn(discountPercentage);
        Assertions.assertEquals(discountPercentage, discountService.findMostProfitablePercentageDiscountForUser(1L));
    }

    @Test
    void testFindMostProfitablePercentageDiscountForUser() {

        when(userService.findUserById(anyLong())).thenReturn(user);
        when(discountPercentageService.findById(any(StatusType.class))).thenReturn(discountPercentage);

        DiscountPercentage result = discountService.findMostProfitablePercentageDiscountForUser(1L);

        assertNotNull(result);
        assertEquals(discountPercentage.getDiscountPercentageValue(), result.getDiscountPercentageValue());
    }

    @Test
    void testCalculateFixedDiscount() {
        BigDecimal totalOrderAmount = new BigDecimal("500");

        BigDecimal result = discountService.calculateFixedDiscount(totalOrderAmount, discountFixed);

        assertEquals(new BigDecimal("25"), result);
    }

    @Test
    void testFindMostProfitableFixedDiscountForUser() {
        when(discountFixedService.findAll()).thenReturn(List.of(discountFixed));
        DiscountFixed result = discountService.findMostProfitableFixedDiscountForUser();

        assertNotNull(result);
        assertEquals(new BigDecimal(100), result.getTargetAmount());
        assertEquals(new BigDecimal(5), result.getRewardAmount());
    }

    @Test
    void calculateProfitRateTest() {
        assertEquals(new BigDecimal("0.05"), discountService.calculateProfitRate(discountFixed));
    }


    @Test
    void testCalculateTotalOrderAmount() {
        OrderDto orderDto = new OrderDto(1L, itemList);

        BigDecimal result = discountService.calculateTotalOrderAmount(orderDto);

        assertEquals(new BigDecimal("350"), result);
    }

    @Test
    void testCalculateTotalOrderAmountWithoutGroceries() {
        OrderDto orderDto = new OrderDto(1L, itemList);

        BigDecimal result = discountService.calculateTotalOrderAmount(orderDto, ItemType.GROCERY);

        assertEquals(new BigDecimal("250"), result);
    }

    @Test
    void testCalculateTotalOrderAmountWithoutGroceries_EmptyList() {
        OrderDto orderDto = new OrderDto(1L, Collections.emptyList());

        BigDecimal result = discountService.calculateTotalOrderAmount(orderDto, ItemType.GROCERY);

        assertEquals(BigDecimal.ZERO, result);
    }


}

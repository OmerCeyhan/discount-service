package com.omerceyhan.discountservice.controller;

import com.omerceyhan.discountservice.dto.DiscountResponse;
import com.omerceyhan.discountservice.dto.OrderDto;
import com.omerceyhan.discountservice.service.discount.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResponse> calculateDiscount(@RequestBody @Valid OrderDto orderDto) {
        return ResponseEntity.ok(discountService.calculateDiscount(orderDto));
    }

}

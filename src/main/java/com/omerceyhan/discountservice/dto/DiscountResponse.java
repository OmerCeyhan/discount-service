package com.omerceyhan.discountservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"totalOrderAmount", "fixedDiscountDescription", "fixedDiscountAmount", "percentageDiscountDescription", "percentageDiscountAmount", "netAmount"})
public class DiscountResponse {

    private BigDecimal totalOrderAmount;

    private String fixedDiscountDescription;

    private BigDecimal fixedDiscountAmount;

    private String percentageDiscountDescription;

    private BigDecimal percentageDiscountAmount;

    private BigDecimal netAmount;
}

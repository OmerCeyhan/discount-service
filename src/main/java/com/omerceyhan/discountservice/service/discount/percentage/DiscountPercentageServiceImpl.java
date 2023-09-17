package com.omerceyhan.discountservice.service.discount.percentage;

import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.StatusType;
import com.omerceyhan.discountservice.repository.DiscountPercentageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountPercentageServiceImpl implements DiscountPercentageService {

    private final DiscountPercentageRepository discountPercentageRepository;

    public DiscountPercentage findById(StatusType statusType) {
        return discountPercentageRepository.findById(statusType).orElse(null);
    }

}

package com.omerceyhan.discountservice.service.discount.fixed;

import com.omerceyhan.discountservice.entity.DiscountFixed;
import com.omerceyhan.discountservice.repository.DiscountFixedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountFixedServiceImpl implements DiscountFixedService {

    private final DiscountFixedRepository discountFixedRepository;

    public List<DiscountFixed> findAll() {
        return discountFixedRepository.findAll();
    }

}

package com.omerceyhan.discountservice.service.discount.percentage;

import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.StatusType;
import com.omerceyhan.discountservice.repository.DiscountPercentageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountPercentageServiceTest {


    @Mock
    private DiscountPercentageRepository discountPercentageRepository;

    @InjectMocks
    private DiscountPercentageServiceImpl discountPercentageService;

    @Test
    void findByIdTest() {
        when(discountPercentageRepository.findById(any())).thenReturn(Optional.of(new DiscountPercentage(StatusType.EMPLOYEE,
                "Employee Discount - 30% Discount", new BigDecimal(30))));
        DiscountPercentage discountPercentage = discountPercentageService.findById(StatusType.EMPLOYEE);
        assertEquals(StatusType.EMPLOYEE, discountPercentage.getStatusType());
        verify(discountPercentageRepository, times(1)).findById(any());

    }
}

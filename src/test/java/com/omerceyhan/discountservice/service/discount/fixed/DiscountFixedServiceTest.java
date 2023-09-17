package com.omerceyhan.discountservice.service.discount.fixed;

import com.omerceyhan.discountservice.repository.DiscountFixedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountFixedServiceTest {
    @Mock
    private DiscountFixedRepository discountFixedRepository;

    @InjectMocks
    private DiscountFixedServiceImpl discountFixedService;

    @Test
    void testFindAll() {
        when(discountFixedRepository.findAll()).thenReturn(List.of());
        discountFixedService.findAll();
        verify(discountFixedRepository, times(1)).findAll();
    }
}

package com.omerceyhan.discountservice.service;


import com.omerceyhan.discountservice.entity.*;
import com.omerceyhan.discountservice.repository.DiscountFixedRepository;
import com.omerceyhan.discountservice.repository.DiscountPercentageRepository;
import com.omerceyhan.discountservice.repository.UserRepository;
import com.omerceyhan.discountservice.repository.UserStatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class InitService {

    private final DiscountFixedRepository discountFixedRepository;
    private final DiscountPercentageRepository discountPercentageRepository;
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @PostConstruct
    public void init() {

        if (userStatusRepository.findAll().isEmpty()) {
            UserStatus employeeUserStatus = userStatusRepository.save(new UserStatus(sequenceGeneratorService.generateSequence(UserStatus.SEQUENCE_NAME), StatusType.EMPLOYEE));
            UserStatus affiliateUserStatus = userStatusRepository.save(new UserStatus(sequenceGeneratorService.generateSequence(UserStatus.SEQUENCE_NAME), StatusType.AFFILIATE));
            UserStatus twoYearsPlusUserStatus = userStatusRepository.save(new UserStatus(sequenceGeneratorService.generateSequence(UserStatus.SEQUENCE_NAME), StatusType.TWO_YEARS_PLUS));

            if (discountPercentageRepository.findAll().isEmpty()) {
                discountPercentageRepository.save(new DiscountPercentage(StatusType.EMPLOYEE,
                        "Employee Discount - 30% Discount", new BigDecimal(30)));
                discountPercentageRepository.save(new DiscountPercentage(StatusType.AFFILIATE,
                        "Affiliate Discount - 10% Discount", new BigDecimal(10)));
                discountPercentageRepository.save(new DiscountPercentage(StatusType.TWO_YEARS_PLUS,
                        "Two Year Customer Discount - 5% Discount", new BigDecimal(5)));
            }
            if (userRepository.findAll().isEmpty()) {
                userRepository.save(new User(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME),
                        "John Wick", Arrays.asList(employeeUserStatus, affiliateUserStatus)));
                userRepository.save(new User(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME),
                        "Frodo Baggins", Arrays.asList(affiliateUserStatus, twoYearsPlusUserStatus)));
                userRepository.save(new User(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME),
                        "Harry Potter", Arrays.asList(twoYearsPlusUserStatus)));
            }
        }
        if (discountFixedRepository.findAll().isEmpty()) {
            discountFixedRepository.save(
                    new DiscountFixed(
                            sequenceGeneratorService.generateSequence(DiscountFixed.SEQUENCE_NAME),
                            "$5 Discount for every $100 spent",
                            new BigDecimal("100"),
                            new BigDecimal("5")));
        }

    }
}

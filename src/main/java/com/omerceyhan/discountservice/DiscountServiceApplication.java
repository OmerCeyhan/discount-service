package com.omerceyhan.discountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DiscountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscountServiceApplication.class, args);
    }

}

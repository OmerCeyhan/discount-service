package com.omerceyhan.discountservice.repository;

import com.omerceyhan.discountservice.entity.DiscountFixed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountFixedRepository extends MongoRepository<DiscountFixed, Long> {
}

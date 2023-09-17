package com.omerceyhan.discountservice.repository;

import com.omerceyhan.discountservice.entity.DiscountPercentage;
import com.omerceyhan.discountservice.entity.StatusType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountPercentageRepository extends MongoRepository<DiscountPercentage, StatusType> {

}

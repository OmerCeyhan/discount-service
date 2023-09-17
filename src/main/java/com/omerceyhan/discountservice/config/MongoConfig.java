package com.omerceyhan.discountservice.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {


    public static MongoClientSettings getMongoClientSettings() {
        ConnectionString connectionString = new ConnectionString("mongodb://mongodb:27017/discount");
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

    }

    @Bean
    public MongoClient mongo() {
        return MongoClients.create(getMongoClientSettings());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "discount");
    }
}

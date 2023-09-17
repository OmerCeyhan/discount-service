package com.omerceyhan.discountservice.service.config;

import com.omerceyhan.discountservice.config.MongoConfig;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MongoConfigTest {


    @InjectMocks
    private MongoConfig mongoConfig;

    @Test
    void mongoTest() {
        MockedStatic<MongoClients> mockMongoClients = Mockito.mockStatic(MongoClients.class);
        mockMongoClients.when(MongoClients::create).thenReturn(null);
        mongoConfig.mongo();
        mockMongoClients.verify(
                () -> MongoClients.create(MongoConfig.getMongoClientSettings()),
                times(1));

    }
}

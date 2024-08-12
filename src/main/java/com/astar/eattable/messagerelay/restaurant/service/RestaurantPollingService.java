package com.astar.eattable.messagerelay.restaurant.service;

import com.astar.eattable.messagerelay.common.repository.ExternalEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class RestaurantPollingService {
    private final ExternalEventRepository externalEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void pollRestaurantEvents() {
        externalEventRepository.findAllByPublishedFalse().forEach(restaurantEvent -> {
//            ExternalRestaurantEvent externalRestaurantEvent = ExternalRestaurantEvent.from(restaurantEvent);
            try {
                CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("restaurant-events", objectMapper.writeValueAsString(restaurantEvent));
                future.thenRun(() -> {
                    restaurantEvent.publish();
                    externalEventRepository.save(restaurantEvent);
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

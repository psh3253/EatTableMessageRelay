package com.astar.eattable.messagerelay.common.service;

import com.astar.eattable.messagerelay.common.dto.EventTypes;
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
public class EventPollingService {
    private final ExternalEventRepository externalEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void pollEvents() {
        externalEventRepository.findAllByPublishedFalse().forEach(externalEvent -> {
            String topic = EventTypes.getTopic(externalEvent.getEventType());
            try {
                CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, objectMapper.writeValueAsString(externalEvent));
                future.thenRun(() -> {
                    externalEvent.publish();
                    externalEventRepository.save(externalEvent);
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

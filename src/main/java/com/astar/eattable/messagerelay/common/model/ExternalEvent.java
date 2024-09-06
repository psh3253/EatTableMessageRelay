package com.astar.eattable.messagerelay.common.model;

import com.astar.eattable.messagerelay.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class ExternalEvent {
    @Id
    @UuidGenerator
    private UUID eventId;

    @NotNull
    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @NotNull
    private boolean published = false;

    @ElementCollection
    @MapKeyColumn(name = "domain")
    @Column(name = "consumed")
    private Map<String, Boolean> domainConsumed = new HashMap<>();

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime publishedAt;

    @Builder
    public ExternalEvent(String eventType, String payload) {
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();
    }

    public void publish() {
        this.published = true;
        this.publishedAt = LocalDateTime.now();
    }
}

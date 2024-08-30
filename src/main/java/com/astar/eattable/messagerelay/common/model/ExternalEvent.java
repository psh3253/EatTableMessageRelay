package com.astar.eattable.messagerelay.common.model;

import com.astar.eattable.messagerelay.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
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

    @NotNull
    private final boolean consumed = false;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime publishedAt;

    private LocalDateTime consumedAt;

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

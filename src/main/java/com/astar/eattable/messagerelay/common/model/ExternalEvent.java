package com.astar.eattable.messagerelay.common.model;

import com.astar.eattable.messagerelay.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Table(name = "external_event")
@Entity
public class ExternalEvent {
    @Id
    @UuidGenerator
    private UUID eventId;

    @NotNull
    private String eventType;

    private Long keyValue;

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

    public void publish() {
        this.published = true;
        this.publishedAt = LocalDateTime.now();
    }
}

package com.astar.eattable.messagerelay.common.repository;

import com.astar.eattable.messagerelay.common.model.ExternalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExternalEventRepository extends JpaRepository<ExternalEvent, UUID> {
    List<ExternalEvent> findAllByPublishedFalseOrderByCreatedAtAsc();
}

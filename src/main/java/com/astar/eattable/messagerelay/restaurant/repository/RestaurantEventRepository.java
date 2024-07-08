package com.astar.eattable.messagerelay.restaurant.repository;

import com.astar.eattable.messagerelay.restaurant.model.RestaurantEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantEventRepository extends JpaRepository<RestaurantEvent, UUID> {
    List<RestaurantEvent> findAllByPublishedFalse();
}

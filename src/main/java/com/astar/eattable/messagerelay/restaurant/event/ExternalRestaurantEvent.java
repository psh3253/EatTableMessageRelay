package com.astar.eattable.messagerelay.restaurant.event;

import com.astar.eattable.messagerelay.common.dto.EventPayload;
import com.astar.eattable.messagerelay.restaurant.model.RestaurantEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalRestaurantEvent extends EventPayload {

    private Long restaurantId;

    private String eventType;

    public static ExternalRestaurantEvent from(RestaurantEvent restaurantEvent) {
        return new ExternalRestaurantEvent(restaurantEvent.getRestaurantId(), restaurantEvent.getEventType());
    }
}

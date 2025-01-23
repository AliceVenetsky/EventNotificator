package org.venetsky.EventNotificator.notification.api;


import org.venetsky.EventNotificator.notification.domain.EventStatus;
import org.venetsky.EventNotificator.notification.domain.FieldChange;

import java.time.LocalDateTime;

public record NotificationDto(
        Long eventId,
        FieldChange<String> name,
        FieldChange<Integer> maxPlaces,
        FieldChange<LocalDateTime> date,
        FieldChange<Integer> cost,
        FieldChange<Integer> duration,
        FieldChange<Long> locationId,
        FieldChange<EventStatus> eventStatus
) {
    @Override
    public String toString() {
        return "NotificationDto{" +
                "eventId=" + eventId +
                ", name=" + name +
                ", maxPlaces=" + maxPlaces +
                ", date=" + date +
                ", cost=" + cost +
                ", duration=" + duration +
                ", locationId=" + locationId +
                ", eventStatus=" + eventStatus +
                '}';
    }
}


package org.venetsky.EventNotificator.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.venetsky.EventNotificator.kafka.EventChangeKafkaMessage;
import org.venetsky.EventNotificator.notification.api.NotificationDto;
import org.venetsky.EventNotificator.notification.db.FieldChangeEntity;
import org.venetsky.EventNotificator.notification.db.NotificationEntity;
import org.venetsky.EventNotificator.notification.domain.EventChangeNotification;
import org.venetsky.EventNotificator.notification.domain.FieldChange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class NotificationConverter {

    public void convertFieldsForEntity(NotificationEntity entity, EventChangeKafkaMessage message) {
        List<FieldChangeEntity> changes = new ArrayList<>();
        entity.setUserId(message.getChangedBy());

        toFieldChangeEntity(changes, "location", message.getLocationId(), entity);
        toFieldChangeEntity(changes, "name", message.getName(), entity);
        toFieldChangeEntity(changes, "maxPlaces", message.getMaxPlaces(), entity);
        toFieldChangeEntity(changes, "cost", message.getCost(), entity);
        toFieldChangeEntity(changes, "duration", message.getDuration(), entity);
        toFieldChangeEntity(changes, "date", message.getDate(), entity);
        toFieldChangeEntity(changes, "eventStatus", message.getStatus(), entity);
        entity.setCreated(LocalDateTime.now());
        entity.setChanges(changes);
    }

    private <T> void toFieldChangeEntity(
            List<FieldChangeEntity> list,
            String name,
            FieldChange<T> field,
            NotificationEntity entity
    ) {
        if (field == null)
            return;

        if (field.getNewValue() != null || field.getNewValue().equals(field.getOldValue())) {
            FieldChangeEntity fieldChangeEntity = new FieldChangeEntity();
            fieldChangeEntity.setFieldName(name);
            fieldChangeEntity.setOldValue(field.getOldValue().toString());
            fieldChangeEntity.setNewValue(field.getNewValue().toString());
            list.add(fieldChangeEntity);
            fieldChangeEntity.setNotification(entity);
        }
    }

    public EventChangeNotification toDomain(NotificationEntity entity) {
        EventChangeNotification notification = new EventChangeNotification();
        notification.setEventId(entity.getEventId());
        for (FieldChangeEntity f : entity.getChanges()) {
            FieldChange fieldChange = new FieldChange(f.getOldValue(), f.getNewValue());
            switch (f.getFieldName()) {
                case "name":
                    notification.setName(fieldChange);
                    break;
                case "location":
                    notification.setLocationId(fieldChange);
                    break;
                case "cost":
                    notification.setCost(fieldChange);
                    break;
                case "duration":
                    notification.setDuration(fieldChange);
                    break;
                case "maxPlaces":
                    notification.setMaxPlaces(fieldChange);
                    break;
                case "date":
                    notification.setDate(fieldChange);
                    break;
                case "eventStatus":
                    notification.setStatus(fieldChange);
                    break;
            }
        }
        return notification;
    }

    public NotificationDto toDto(EventChangeNotification notification) {
        return new NotificationDto(
                notification.getEventId(),
                notification.getName(),
                notification.getMaxPlaces(),
                notification.getDate(),
                notification.getCost(),
                notification.getDuration(),
                notification.getLocationId(),
                notification.getStatus()
        );
    }
}

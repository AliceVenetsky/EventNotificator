package org.venetsky.EventNotificator.notification.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.venetsky.EventNotificator.converters.NotificationConverter;
import org.venetsky.EventNotificator.kafka.EventChangeKafkaMessage;
import org.venetsky.EventNotificator.notification.db.NotificationEntity;
import org.venetsky.EventNotificator.notification.db.NotificationRepository;
import org.venetsky.EventNotificator.user.AuthenticationService;

import java.util.List;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final AuthenticationService authenticationService;
    private final NotificationRepository notificationRepository;
    private final NotificationConverter converter;


    public NotificationService(
            AuthenticationService authenticationService,
            NotificationRepository notificationRepository,
            NotificationConverter converter
    ) {
        this.authenticationService = authenticationService;
        this.notificationRepository = notificationRepository;
        this.converter = converter;
    }

    public List<EventChangeNotification> getAllNotificationsByUser() {
        var user = authenticationService.getCurrentUser();
        return notificationRepository.findNotificationsIsNotRead(user.id())
                .stream()
                .map(converter::toDomain)
                .toList();
    }

    public void saveNotification(EventChangeKafkaMessage message) {
        log.info("Save message = {} to db", message);
        NotificationEntity entity = new NotificationEntity();
        entity.setEventId(message.getEventId());
        converter.convertFieldsForEntity(entity, message);
        notificationRepository.save(entity);
    }

    public void readNotifications(List<Long> notificationIds) {
        for (Long id : notificationIds) {
            notificationRepository.findById(id).ifPresent(n -> {
                notificationRepository.updateNotificationIsRead(id);
            });
        }
    }
}

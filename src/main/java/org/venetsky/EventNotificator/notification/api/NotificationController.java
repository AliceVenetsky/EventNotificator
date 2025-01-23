package org.venetsky.EventNotificator.notification.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.venetsky.EventNotificator.converters.NotificationConverter;
import org.venetsky.EventNotificator.notification.domain.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);
    private final NotificationService notificationService;
    private final NotificationConverter converter;

    public NotificationController(
            NotificationService notificationService,
            NotificationConverter converter
    ) {
        this.notificationService = notificationService;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<Void> setNotificationsIsRead(
            @RequestBody ReadNotificationList notificationIds) {
        log.info("Get request to set notification: {} is read", notificationIds);
        notificationService.readNotifications(notificationIds.notificationIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotificationsByUser() {
        log.info("Get request to show all user notifications");
        return ResponseEntity.ok()
                .body(notificationService.getAllNotificationsByUser()
                        .stream()
                        .map(converter::toDto)
                        .toList());
    }

}

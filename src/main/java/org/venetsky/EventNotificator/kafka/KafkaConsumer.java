package org.venetsky.EventNotificator.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.venetsky.EventNotificator.notification.domain.NotificationService;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "event-notification", containerFactory = "containerFactory")
    public void listenEvents(
            ConsumerRecord<Long, EventChangeKafkaMessage> record
    ) {
        log.info("get new notification: event={}", record.value());
        notificationService.saveNotification(record.value());
    }

}

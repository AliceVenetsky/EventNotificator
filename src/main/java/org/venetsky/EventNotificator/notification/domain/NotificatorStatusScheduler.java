package org.venetsky.EventNotificator.notification.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.venetsky.EventNotificator.notification.db.NotificationRepository;

import java.time.LocalDateTime;


@Service
@EnableScheduling
public class NotificatorStatusScheduler {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepository;
    private final long clearPeriod;

    public NotificatorStatusScheduler(
            NotificationRepository notificationRepository,
            @Value("${scheduler.period}") long clearPeriod
    ) {
        this.notificationRepository = notificationRepository;
        this.clearPeriod = clearPeriod;
    }

    @Scheduled(cron = "${event.stats.cron}")
    @Transactional
    public void clearNotifications() {
        LocalDateTime period = LocalDateTime.now().minusDays(clearPeriod);
        var list = notificationRepository.findByCreatedDateBefore(period);
        log.info("list to be deleted {}", list);
        notificationRepository.deleteByCreatedDateBefore(period);
        log.info("Notifications deleted");
    }
}

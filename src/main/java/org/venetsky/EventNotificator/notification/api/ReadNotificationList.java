package org.venetsky.EventNotificator.notification.api;

import java.util.List;

public record ReadNotificationList(
        List<Long> notificationIds
) {

}

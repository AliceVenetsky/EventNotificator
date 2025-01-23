package org.venetsky.EventNotificator.notification.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Query("""
            SELECT n from NotificationEntity n
            WHERE n.userId = :id
            AND n.isRead = FALSE
            """)
    List<NotificationEntity> findNotificationsIsNotRead(@Param("id") Long userId);

    List<NotificationEntity> findByCreatedDateBefore(LocalDateTime dateTime);

    // @Modifying
    // @Query(value = "DELETE FROM notifications n WHERE n.created_date < :date", nativeQuery = true)
    void deleteByCreatedDateBefore(LocalDateTime dateTime);

    @Modifying
    @Transactional
    @Query(""" 
            UPDATE NotificationEntity e
            SET e.isRead = TRUE
            WHERE e.id = :id
            """)
    void updateNotificationIsRead(@Param("id") Long id);
}

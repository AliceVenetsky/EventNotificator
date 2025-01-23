package org.venetsky.EventNotificator.notification.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "event_id", nullable = false)
    private Long eventId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FieldChangeEntity> changes;
    @Column(name = "is_read")
    private boolean isRead;

    public NotificationEntity() {
    }

    public NotificationEntity(
            Long id,
            Long userId,
            LocalDateTime date,
            List<FieldChangeEntity> changes,
            boolean isRead
    ) {
        this.id = id;
        this.userId = userId;
        this.createdDate = LocalDateTime.now();
        this.changes = changes;
        this.isRead = false;
    }

    public void setChanges(List<FieldChangeEntity> changes) {
        this.changes = changes;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<FieldChangeEntity> getChanges() {
        return changes;
    }

    public Long getEventId() {
        return eventId;
    }

    public LocalDateTime getDate() {
        return createdDate;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setCreated(LocalDateTime created) {
        this.createdDate = created;
    }
}

package org.venetsky.EventNotificator.notification.db;

import jakarta.persistence.*;

@Entity
@Table(name = "changes")
public class FieldChangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "field_name")
    private String fieldName;
    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private NotificationEntity notification;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "new_value")
    private String newValue;

    public FieldChangeEntity() {
    }

    public FieldChangeEntity(
            Long id,
            String fieldName,
            NotificationEntity notification,
            String oldValue,
            String newValue
    ) {
        this.id = id;
        this.fieldName = fieldName;
        this.notification = notification;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setNotification(NotificationEntity notification) {
        this.notification = notification;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }
}

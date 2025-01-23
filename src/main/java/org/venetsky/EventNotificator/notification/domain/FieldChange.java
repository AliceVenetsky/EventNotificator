package org.venetsky.EventNotificator.notification.domain;


public class FieldChange<T> {
    private T oldValue;
    private T newValue;

    public FieldChange() {
    }

    public FieldChange(T oldValue, T newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "{" +
                "oldField: " + oldValue.toString() +
                ", newField: " + newValue.toString() +
                '}';
    }

    public T getOldValue() {
        return oldValue;
    }

    public void setOldValue(T oldValue) {
        this.oldValue = oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    public void setNewValue(T newValue) {
        this.newValue = newValue;
    }
}

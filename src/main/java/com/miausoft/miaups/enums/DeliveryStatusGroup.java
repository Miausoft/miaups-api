package com.miausoft.miaups.enums;

public enum DeliveryStatusGroup {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

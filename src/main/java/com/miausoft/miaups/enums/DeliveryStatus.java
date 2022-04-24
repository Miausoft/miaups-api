package com.miausoft.miaups.enums;

public enum DeliveryStatus {
    AWAITING,
    PICKED_UP,
    DELIVERED;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

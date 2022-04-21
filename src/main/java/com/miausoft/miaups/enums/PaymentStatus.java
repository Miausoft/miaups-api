package com.miausoft.miaups.enums;

public enum PaymentStatus {
    PENDING,
    COMPLETED,
    FAILED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

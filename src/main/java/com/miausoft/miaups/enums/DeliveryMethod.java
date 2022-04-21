package com.miausoft.miaups.enums;

public enum DeliveryMethod {
    HOME_TO_HOME,
    HOME_TO_PARCEL_MACHINE,
    PARCEL_MACHINE_TO_PARCEL_MACHINE,
    PARCEL_MACHINE_TO_HOME;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

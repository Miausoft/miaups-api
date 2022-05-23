package com.miausoft.miaups.enums;

public enum DeliveryMethod {
    HOME_TO_WAREHOUSE,
    PARCEL_MACHINE_TO_WAREHOUSE,
    WAREHOUSE_TO_PARCEL_MACHINE,
    WAREHOUSE_TO_WAREHOUSE,
    WAREHOUSE_TO_HOME,

    // Customer can select only these 4 methods of delivery
    HOME_TO_HOME,
    HOME_TO_PARCEL_MACHINE,
    PARCEL_MACHINE_TO_PARCEL_MACHINE,
    PARCEL_MACHINE_TO_HOME;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

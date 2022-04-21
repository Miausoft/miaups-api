package com.miausoft.miaups.enums;

public enum DeliveryStatus {
    /*
        Awaiting to be picked up from the client or parcel machine.
        Probably need to split into two different statuses.
     */
    AWAITING,

    /*
        Picked up from the client.
     */
    ACCEPTED_FROM_CLIENT,

    /*
        Picked up from the parcel machine.
     */
    ACCEPTED_FROM_PARCEL_MACHINE,

    /*
        The parcel is accepted and is in process.
        Probably need many more statuses to represent better the parcel process.
        It can be with a courier on a road, in a warehouse, on a plane probably...
     */
    ACCEPTED_IN_PROCESS,

    /*
        The parcel has been delivered to the destination address but yet not claimed.
     */
    DELIVERED,

    /*
        Parcel has been picked up by the client.
     */
    CLAIMED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

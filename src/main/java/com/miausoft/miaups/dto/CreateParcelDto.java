package com.miausoft.miaups.dto;

import com.miausoft.miaups.enums.DeliveryMethod;
import com.miausoft.miaups.persistence.entities.Address;

public class CreateParcelDto {
    public DeliveryMethod deliveryMethod;
    public long dimensionsId;
    public Address startAddress;
    public Address destinationAddress;
    public Long startParcelMachineId;
    public Long destinationParcelMachineId;
}

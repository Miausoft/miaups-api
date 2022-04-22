package com.miausoft.miaups.dto;

import com.miausoft.miaups.persistence.entities.Address;

public class CreateParcelDto {
    public Integer deliveryTypeId;
    public Integer dimensionsId;
    public Address startAddress;
    public Address destinationAddress;
    public Integer startParcelMachineId;
    public Integer destinationParcelMachineId;
}

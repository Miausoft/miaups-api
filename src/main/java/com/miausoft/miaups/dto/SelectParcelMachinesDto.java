package com.miausoft.miaups.dto;

import com.miausoft.miaups.persistence.entities.Address;

public class SelectParcelMachinesDto {
    public long id;
    public Address address;
    public boolean hasEmptyLocker;
}

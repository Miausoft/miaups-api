package com.miausoft.miaups.mappers;

import com.miausoft.miaups.dto.SelectParcelMachinesDto;
import com.miausoft.miaups.persistence.entities.ParcelMachine;
import com.miausoft.miaups.persistence.entities.ParcelMachineLocker;
import org.springframework.stereotype.Component;

@Component
public class ParcelMachinesMappers {

    public SelectParcelMachinesDto toSelectParcelMachineDto(ParcelMachine parcelMachine){
        SelectParcelMachinesDto dto = new SelectParcelMachinesDto();
        dto.address = parcelMachine.getAddress();
        dto.id = parcelMachine.getId();
        for (ParcelMachineLocker locker: parcelMachine.getLockers()) {
            if(locker.getStoredParcel() == null){
                dto.hasEmptyLocker = true;
                break;
            }
        }
        return dto;
    }
}

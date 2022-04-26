package com.miausoft.miaups.mappers;

import com.miausoft.miaups.dto.ReturnParcelMachinesDto;
import com.miausoft.miaups.persistence.entities.ParcelMachine;
import org.springframework.stereotype.Component;

@Component
public class ParcelMachinesMappers {

    public ReturnParcelMachinesDto toReturnParcelMachinesDto(ParcelMachine parcelMachine) {
        return new ReturnParcelMachinesDto(
                parcelMachine.id,
                parcelMachine.getAddress().toString(),
                parcelMachine.getAvailableLockersCount() == 0
        );
    }
}

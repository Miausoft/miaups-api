package com.miausoft.miaups.mappers;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.enums.DeliveryMethod;
import com.miausoft.miaups.persistence.ParcelDimensionsRepository;
import com.miausoft.miaups.persistence.ParcelMachinesRepository;
import com.miausoft.miaups.persistence.entities.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParcelMappers {
    @Autowired
    ParcelDimensionsRepository parcelDimensionsRepository;
    @Autowired
    ParcelMachinesRepository parcelMachinesRepository;

    public Parcel fromCreateDtoToParcel(CreateParcelDto dto) {
        Parcel parcel = new Parcel();
        parcel.setDeliveryMethod(DeliveryMethod.values()[dto.deliveryTypeId]);
        parcel.setDimensions(parcelDimensionsRepository.getById(dto.dimensionsId));
        parcel.setStartAddress(dto.startAddress);
        parcel.setDestinationAddress(dto.destinationAddress);

        if (dto.startParcelMachineId != null) {
            parcel.setStartParcelMachine(
                    parcelMachinesRepository.getById(dto.startParcelMachineId));

        }
        if (dto.destinationParcelMachineId != null) {
            parcel.setDestinationParcelMachine(
                    parcelMachinesRepository.getById(dto.destinationParcelMachineId));
        }

        return parcel;
    }
}

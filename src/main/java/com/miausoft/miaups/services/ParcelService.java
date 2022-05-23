package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.persistence.ParcelDimensionsRepository;
import com.miausoft.miaups.persistence.ParcelMachinesRepository;
import com.miausoft.miaups.persistence.ParcelsRepository;
import com.miausoft.miaups.persistence.entities.Parcel;
import com.miausoft.miaups.persistence.entities.ParcelMachineLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {
    @Autowired
    ParcelsRepository parcelsRepository;
    @Autowired
    ParcelDimensionsRepository parcelDimensionsRepository;
    @Autowired
    ParcelMachinesRepository parcelMachinesRepository;
    @Autowired
    ParcelMachineService parcelMachineService;

    public Parcel createParcel(CreateParcelDto dto) {
        Parcel parcel = new Parcel();
        parcel.setDeliveryMethod(dto.deliveryMethod);
        parcel.setDimensions(parcelDimensionsRepository.getById(dto.dimensionsId));
        parcel.setStartAddress(dto.startAddress);
        parcel.setDestinationAddress(dto.destinationAddress);

        if (dto.startParcelMachineId != null) {
            parcel.setStartParcelMachine(parcelMachinesRepository.getById(dto.startParcelMachineId));
            ParcelMachineLocker locker = parcelMachineService.reserveEmptyLocker(parcel.getStartParcelMachine());
        }

        if (dto.destinationParcelMachineId != null) {
            parcel.setDestinationParcelMachine(parcelMachinesRepository.getById(dto.destinationParcelMachineId));
            parcelMachineService.reserveEmptyLocker(parcel.getDestinationParcelMachine());
        }

        return parcel;
    }

    public Parcel getById(UUID id) {
        return parcelsRepository.findById(id).orElseThrow();
    }

    public List<Parcel> getAll() {
        return parcelsRepository.findAll();
    }

    public void save(Parcel parcel){
        parcelsRepository.save(parcel);
    }
}

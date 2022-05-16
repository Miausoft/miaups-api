package com.miausoft.miaups.services;

import com.miausoft.miaups.persistence.ParcelMachineLockersRepository;
import com.miausoft.miaups.persistence.ParcelMachinesRepository;
import com.miausoft.miaups.persistence.entities.Parcel;
import com.miausoft.miaups.persistence.entities.ParcelMachine;
import com.miausoft.miaups.persistence.entities.ParcelMachineLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelMachineService {
    @Autowired
    ParcelMachinesRepository machinesRepository;
    @Autowired
    ParcelMachineLockersRepository lockersRepository;

    public ParcelMachineLocker reserveEmptyLocker(ParcelMachine machine) {
        ParcelMachineLocker locker = machine.reserveFreeLocker();
        machinesRepository.save(machine);
        return locker;
    }

    public ParcelMachineLocker reserveEmptyLocker(Long machineId) {
        return reserveEmptyLocker(machinesRepository.findById(machineId).orElseThrow());
    }

    public ParcelMachineLocker getReservedAndEmptyLocker(ParcelMachine machine) {
        return lockersRepository.getReservedAndEmptyLocker(machine.getId());
    }
    public void removeParcelFromLocker(ParcelMachineLocker locker) {
        locker.removeParcel();
        machinesRepository.save(locker.getParcelMachine());
    }

    public void insertParcelIntoLocker(Parcel parcel, ParcelMachine machine) {
        ParcelMachineLocker locker = lockersRepository.getReservedAndEmptyLocker(machine.getId());
        insertParcelIntoLocker(parcel, locker);
    }

    public void insertParcelIntoLocker(Parcel parcel, ParcelMachineLocker locker) {
        locker.insertParcel(parcel);
        lockersRepository.save(locker);
    }
}

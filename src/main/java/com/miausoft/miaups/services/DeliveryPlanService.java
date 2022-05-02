package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateDeliveryPlanDto;
import com.miausoft.miaups.persistence.*;
import com.miausoft.miaups.persistence.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPlanService {
    @Autowired
    ParcelsRepository parcelsRepository;
    @Autowired
    ParcelMachinesRepository parcelMachinesRepository;
    @Autowired
    ParcelMachineLockersRepository lockersRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    DeliveryTasksRepository deliveryTasksRepository;

    public void create(CreateDeliveryPlanDto dto) {
        Parcel parcel = parcelsRepository.findById(dto.parcelId).orElseThrow();
        DeliveryTask deliveryTask;
        ParcelMachineLocker startLocker = null, destLocker = null;
        Warehouse startWarehouse = null, destWarehouse = null;

        if (parcel.getStartParcelMachine() != null) {
            startLocker = lockersRepository.getFreeLocker(parcel.getStartParcelMachine().getId());
            startLocker.setReserved(true);
        }

        if (dto.stops.isEmpty()) {
            if (parcel.getDestinationParcelMachine() != null) {
                destLocker = lockersRepository.getFreeLocker(parcel.getDestinationParcelMachine().getId());
                destLocker.setReserved(true);
            }

            deliveryTask = new DeliveryTask(1, parcel,
                    parcel.getStartAddress(), parcel.getDestinationAddress(),
                    startLocker, destLocker,
                    null, null);
            deliveryTasksRepository.save(deliveryTask);
        } else {
            //From start to first stop
            if(dto.stops.get(0).warehouseId != null){
                destWarehouse = warehouseRepository.findById(dto.stops.get(0).warehouseId).orElse(null);
            }else{
                destLocker = lockersRepository.getFreeLocker(dto.stops.get(0).parcelMachineId);
                destLocker.getParcelMachine().decreaseAvailableLockersCount();
                destLocker.setReserved(true);
            }

            deliveryTask = new DeliveryTask(1, parcel,
                    parcel.getStartAddress(), null,
                    startLocker, destLocker,
                    null, destWarehouse);
            deliveryTasksRepository.save(deliveryTask);

            //Stops
            startWarehouse = destWarehouse;
            startLocker = destLocker;
            destWarehouse = null;
            destLocker = null;
            for (int i = 0; i < dto.stops.size() - 1; i++) {
                if(dto.stops.get(i+1).warehouseId != null){
                    destWarehouse = warehouseRepository.findById(dto.stops.get(i + 1).warehouseId).orElse(null);
                }else{
                    destLocker = lockersRepository.getFreeLocker(dto.stops.get(i + 1).parcelMachineId);
                    destLocker.getParcelMachine().decreaseAvailableLockersCount();
                    destLocker.setReserved(true);
                }

                deliveryTask = new DeliveryTask(i + 2, parcel,
                        null, null,
                        startLocker, destLocker,
                        startWarehouse, destWarehouse);
                deliveryTasksRepository.save(deliveryTask);

                startWarehouse = destWarehouse;
                startLocker = destLocker;
                destWarehouse = null;
                destLocker = null;
            }
            //From Last Stop to Dest
            if (parcel.getDestinationParcelMachine() != null) {
                destLocker = lockersRepository.getFreeLocker(parcel.getDestinationParcelMachine().getId());
                destLocker.setReserved(true);
            }

            deliveryTask = new DeliveryTask(dto.stops.size()+1, parcel,
                    null, parcel.getDestinationAddress(),
                    startLocker, destLocker,
                    startWarehouse, null);

            deliveryTasksRepository.save(deliveryTask);
        }
    }
}

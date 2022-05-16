package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateDeliveryPlanDto;
import com.miausoft.miaups.persistence.DeliveryTasksRepository;
import com.miausoft.miaups.persistence.WarehouseRepository;
import com.miausoft.miaups.persistence.entities.DeliveryTask;
import com.miausoft.miaups.persistence.entities.Parcel;
import com.miausoft.miaups.persistence.entities.ParcelMachineLocker;
import com.miausoft.miaups.persistence.entities.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryPlanService {
    @Autowired
    ParcelService parcelService;
    @Autowired
    ParcelMachineService parcelMachineService;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    DeliveryTasksRepository deliveryTasksRepository;

    public void createPlan(CreateDeliveryPlanDto dto) {
        Parcel parcel = parcelService.getById(dto.parcelId);
        List<DeliveryTask> tasks = new ArrayList<>();
        ParcelMachineLocker startLocker = parcel.getCurrentParcelMachineLocker(), destLocker = null;
        Warehouse startWarehouse = null, destWarehouse = null;

        if (dto.stops.isEmpty()) {
            if (parcel.getDestinationParcelMachine() != null) {
                destLocker = parcelMachineService.getReservedAndEmptyLocker(parcel.getDestinationParcelMachine());
            }
            tasks.add(new DeliveryTask(
                    1, parcel,
                    parcel.getStartAddress(), parcel.getDestinationAddress(),
                    startLocker, destLocker,
                    null, null));
        } else {
            //From start to first stop
            if (dto.stops.get(0).warehouseId != null) {
                destWarehouse = warehouseRepository.findById(dto.stops.get(0).warehouseId).orElseThrow();
            } else if (dto.stops.get(0).parcelMachineId != null) {
                destLocker = parcelMachineService.reserveEmptyLocker(dto.stops.get(0).parcelMachineId);
            } else {
                throw new RuntimeException("WarehouseId or ParcelMachineId must be non-null");
            }

            tasks.add(new DeliveryTask(
                    1, parcel,
                    parcel.getStartAddress(), null,
                    startLocker, destLocker,
                    null, destWarehouse));

            //Stops
            startWarehouse = destWarehouse;
            startLocker = destLocker;
            destWarehouse = null;
            destLocker = null;
            for (int i = 0; i < dto.stops.size() - 1; i++) {
                if (dto.stops.get(i + 1).warehouseId != null) {
                    destWarehouse = warehouseRepository.findById(dto.stops.get(i + 1).warehouseId).orElseThrow();
                } else if (dto.stops.get(i + 1).parcelMachineId != null) {
                    destLocker = parcelMachineService.reserveEmptyLocker(dto.stops.get(i + 1).parcelMachineId);
                } else {
                    throw new RuntimeException("WarehouseId or ParcelMachineId must be non-null");
                }

                tasks.add(new DeliveryTask(
                        i + 2, parcel,
                        null, null,
                        startLocker, destLocker,
                        startWarehouse, destWarehouse));

                startWarehouse = destWarehouse;
                startLocker = destLocker;
                destWarehouse = null;
                destLocker = null;
            }
            //From Last Stop to Dest
            if (parcel.getDestinationParcelMachine() != null) {
                destLocker = parcelMachineService.getReservedAndEmptyLocker(parcel.getDestinationParcelMachine());
            }

            tasks.add(new DeliveryTask(
                    dto.stops.size() + 1, parcel,
                    null, parcel.getDestinationAddress(),
                    startLocker, destLocker,
                    startWarehouse, null));
        }
        deliveryTasksRepository.saveAll(tasks);
    }
}

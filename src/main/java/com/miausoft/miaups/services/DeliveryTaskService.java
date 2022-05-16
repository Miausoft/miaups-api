package com.miausoft.miaups.services;

import com.miausoft.miaups.enums.DeliveryStatus;
import com.miausoft.miaups.persistence.DeliveryTaskRecordRepository;
import com.miausoft.miaups.persistence.DeliveryTasksRepository;
import com.miausoft.miaups.persistence.entities.DeliveryTask;
import com.miausoft.miaups.persistence.entities.DeliveryTaskRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTaskService {
    @Autowired
    DeliveryTasksRepository deliveryTasksRepository;
    @Autowired
    DeliveryTaskRecordRepository deliveryTaskRecordRepository;
    @Autowired
    ParcelMachineService parcelMachineService;
    @Autowired
    ParcelService parcelService;

    public void setDeliveryTaskStarted(Long id) {
        DeliveryTask task = setStatus(id, DeliveryStatus.PICKED_UP);

        if (task.getStartWarehouse() != null) {
            task.getParcel().setCurrentWarehouse(null);
        } else if (task.getStartParcelMachineLocker() != null) {
            parcelMachineService.removeParcelFromLocker(task.getStartParcelMachineLocker());
            task.getParcel().setCurrentParcelMachineLocker(null);
        }
        parcelService.save(task.getParcel());
    }

    public void setDeliveryTaskCompleted(Long id) {
        DeliveryTask task = setStatus(id, DeliveryStatus.DELIVERED);

        if (task.getDestinationWarehouse() != null) {
            task.getParcel().setCurrentWarehouse(task.getDestinationWarehouse());
            parcelService.save(task.getParcel());
        } else if (task.getDestinationParcelMachineLocker() != null) {
            parcelMachineService.insertParcelIntoLocker(task.getParcel(), task.getDestinationParcelMachineLocker());
        }
    }

    public DeliveryTask setStatus(Long id, DeliveryStatus status) {
        DeliveryTask task = deliveryTasksRepository.findById(id).orElseThrow();
        task.setStatus(status);
        DeliveryTaskRecord record = new DeliveryTaskRecord(status, task, task.getParcel());
        deliveryTaskRecordRepository.save(record);
        return task;
    }
}

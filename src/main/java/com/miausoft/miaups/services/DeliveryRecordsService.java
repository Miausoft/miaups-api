package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.DeliveryRecordDto;
import com.miausoft.miaups.enums.DeliveryStatus;
import com.miausoft.miaups.persistence.DeliveryTaskRecordRepository;
import com.miausoft.miaups.persistence.entities.DeliveryTaskRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryRecordsService {
    @Autowired
    DeliveryTaskRecordRepository repository;

    public List<DeliveryRecordDto> getAll(UUID parcelId) {
        List<DeliveryTaskRecord> records = repository.getAllByParcelIdOrderByCreatedAt(parcelId);
        List<DeliveryRecordDto> result = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for (DeliveryTaskRecord record : records) {
            LocalDateTime ldt = record.getCreatedAt();
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            if (record.getDeliveryStatus() == DeliveryStatus.PICKED_UP) {
                result.add(new DeliveryRecordDto("Picked up from: " + record.getDelivery().getHumanReadableStartAddress(), dateFormat.format(out), true));
            } else if (record.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
                result.add(new DeliveryRecordDto("Delivered to: " + record.getDelivery().getHumanReadableStartAddress(), dateFormat.format(out), true));
            } else {
                throw new RuntimeException("Record status can be only PICKED_UP or DELIVERED");
            }
        }
        return result;
    }
}

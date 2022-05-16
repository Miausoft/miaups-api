package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.DeliveryTaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryTaskRecordRepository extends JpaRepository<DeliveryTaskRecord,Long>
{
    List<DeliveryTaskRecord> getAllByParcelIdOrderByCreatedAt(UUID parcelId);
}

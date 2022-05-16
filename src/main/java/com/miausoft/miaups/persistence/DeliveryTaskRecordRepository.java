package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.DeliveryTaskRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTaskRecordRepository extends JpaRepository<DeliveryTaskRecord,Long>
{
}

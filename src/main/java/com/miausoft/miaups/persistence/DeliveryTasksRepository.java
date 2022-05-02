package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.DeliveryTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryTasksRepository extends JpaRepository<DeliveryTask,Long> {
}

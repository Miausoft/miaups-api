package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
}

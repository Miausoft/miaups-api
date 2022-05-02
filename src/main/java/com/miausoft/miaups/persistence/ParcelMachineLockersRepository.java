package com.miausoft.miaups.persistence;

import com.miausoft.miaups.persistence.entities.ParcelMachineLocker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParcelMachineLockersRepository extends JpaRepository<ParcelMachineLocker,Long> {

    @Query(value = "SELECT * FROM parcel_machine_locker WHERE parcel_machine_id = ?1 AND reserved = false LIMIT 1",nativeQuery = true)
    ParcelMachineLocker getFreeLocker(Long parcelMachineId);
}

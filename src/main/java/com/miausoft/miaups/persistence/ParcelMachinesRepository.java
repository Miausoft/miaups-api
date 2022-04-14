package com.miausoft.miaups.persistence;

import com.miausoft.miaups.models.ParcelMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelMachinesRepository extends JpaRepository<ParcelMachine,Integer> {
}

package com.miausoft.miaups.persistence;


import com.miausoft.miaups.persistence.entities.ParcelDimensions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelDimensionsRepository extends JpaRepository<ParcelDimensions, Integer> {
}

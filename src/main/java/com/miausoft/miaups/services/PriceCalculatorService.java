package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.persistence.ParcelDimensionsRepository;
import com.miausoft.miaups.persistence.entities.ParcelDimensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PriceCalculatorService {
    @Autowired
    private ParcelDimensionsRepository parcelDimensionsRepository;

    public BigDecimal calculatePrice(CreateParcelDto createParcelDto) {
        ParcelDimensions parcelDimensions = parcelDimensionsRepository.findById(createParcelDto.dimensionsId).orElseThrow();

        return new BigDecimal(parcelDimensions.getPrice()).setScale(2, RoundingMode.HALF_UP);
    }
}

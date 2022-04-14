package com.miausoft.miaups.services;

import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.models.ParcelDimensions;
import com.miausoft.miaups.persistence.ParcelDimensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService {

    @Autowired
    private ParcelDimensionsRepository parcelDimensionsRepository;

    public Float calculatePrice(CreateParcelDto createParcelDto) {
        ParcelDimensions parcelDimensions = parcelDimensionsRepository.findById(createParcelDto.dimensionsId).orElseThrow();
        return parcelDimensions.getPrice();
    }
}

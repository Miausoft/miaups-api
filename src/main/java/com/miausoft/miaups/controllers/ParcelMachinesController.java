package com.miausoft.miaups.controllers;

import com.miausoft.miaups.mappers.ParcelMachinesMappers;
import com.miausoft.miaups.persistence.ParcelMachinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/parcelmachines")
public class ParcelMachinesController {
    @Autowired
    ParcelMachinesMappers parcelMachinesMappers;
    @Autowired
    ParcelMachinesRepository parcelMachinesRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAll() {

        return new ResponseEntity(
                parcelMachinesRepository.findAll().stream()
                        .map(x -> parcelMachinesMappers.toSelectParcelMachineDto(x)),
                HttpStatus.OK);
    }
}

package com.miausoft.miaups.controllers;

import com.miausoft.miaups.persistence.ParcelDimensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/parceldimensions")
public class ParcelDimensionsController {
    @Autowired
    ParcelDimensionsRepository parcelDimensionsRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(){
        return new ResponseEntity(parcelDimensionsRepository.findAll(), HttpStatus.OK);
    }
}

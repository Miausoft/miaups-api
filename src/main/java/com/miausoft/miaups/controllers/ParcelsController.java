package com.miausoft.miaups.controllers;

import com.miausoft.miaups.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/parcels")
public class ParcelsController {
    @Autowired
    ParcelService parcelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getById(@RequestParam("id") UUID id) {
        try {
            return new ResponseEntity(parcelService.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return new ResponseEntity(parcelService.getAll(), HttpStatus.OK);
    }
}

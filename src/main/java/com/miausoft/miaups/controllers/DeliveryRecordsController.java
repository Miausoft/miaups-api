package com.miausoft.miaups.controllers;

import com.miausoft.miaups.services.DeliveryRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/deliveryRecords")
public class DeliveryRecordsController {
    @Autowired
    DeliveryRecordsService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam("id") UUID id) {
        return new ResponseEntity(service.getAll(id), HttpStatus.OK);
    }

}

package com.miausoft.miaups.controllers;

import com.miausoft.miaups.dto.CreateDeliveryPlanDto;
import com.miausoft.miaups.persistence.ParcelsRepository;
import com.miausoft.miaups.services.DeliveryPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/parcels")
public class ParcelsController {
    @Autowired
    ParcelsRepository parcelsRepository;

    @Autowired
    DeliveryPlanService deliveryPlanService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getById(@RequestParam("id") UUID id) {
        try {
            return new ResponseEntity(parcelsRepository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity(parcelsRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deliveryplan", method = RequestMethod.POST)
    public ResponseEntity createDeliveryPlan(@RequestBody CreateDeliveryPlanDto dto) {
        deliveryPlanService.create(dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}

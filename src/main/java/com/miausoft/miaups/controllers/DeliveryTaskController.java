package com.miausoft.miaups.controllers;

import com.miausoft.miaups.dto.CreateDeliveryPlanDto;
import com.miausoft.miaups.services.DeliveryPlanService;
import com.miausoft.miaups.services.DeliveryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deliverytask")
public class DeliveryTaskController {
    @Autowired
    DeliveryPlanService deliveryPlanService;
    @Autowired
    DeliveryTaskService service;

    @RequestMapping(value = "/create/plan", method = RequestMethod.POST)
    public ResponseEntity createDeliveryPlan(@RequestBody CreateDeliveryPlanDto dto) {
        deliveryPlanService.createPlan(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/pickup", method = RequestMethod.POST)
    public ResponseEntity deliveryTaskStarted(@RequestParam Long id) {
        service.setDeliveryTaskStarted(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public ResponseEntity deliveryTaskCompleted(@RequestParam Long id) {
        service.setDeliveryTaskCompleted(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

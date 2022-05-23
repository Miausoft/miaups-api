package com.miausoft.miaups.controllers;

import com.miausoft.miaups.persistence.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/warehouses")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        return new ResponseEntity(warehouseRepository.findAll(), HttpStatus.OK);
    }
}
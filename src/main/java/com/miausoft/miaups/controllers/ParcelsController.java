package com.miausoft.miaups.controllers;

import com.miausoft.miaups.Role;
import com.miausoft.miaups.dto.CreateParcelDto;
import com.miausoft.miaups.dto.UpdateParcelDto;
import com.miausoft.miaups.interceptors.Authorize;
import com.miausoft.miaups.services.ParcelService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.util.Map;
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
    @Authorize(roles = {Role.ADMIN})
    public ResponseEntity getAll() {
        return new ResponseEntity(parcelService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @Authorize(roles = {Role.ADMIN})
    public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody UpdateParcelDto parcelDto) {

        try {
            parcelService.update(id, parcelDto);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return ResponseEntity.badRequest().body(
                Map.of(
                        "error", "OPTIMISTIC_LOCK"
                )
            );
        }
        return ResponseEntity.ok().build();
    }
}

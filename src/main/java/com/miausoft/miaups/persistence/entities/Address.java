package com.miausoft.miaups.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Address implements Serializable {
    private String streetName;
    private String buildingNumber;
    private String postCode;
    private String townName;
    private String countryCode;
}

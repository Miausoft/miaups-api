package com.miausoft.miaups.persistence.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Address implements Serializable {
    private String countryCode = "LT";
    private String postCode;
    private String townName;
    private String streetName;
    private String buildingNumber;

    @Override
    @JsonValue
    public String toString() {
        return countryCode + "-" + postCode + " " + townName + " " + streetName + " " + buildingNumber;
    }
}

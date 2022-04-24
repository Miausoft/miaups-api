package com.miausoft.miaups.converter;

import com.miausoft.miaups.persistence.entities.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address attribute) {
        if(attribute == null){
            return null;
        }

        return attribute.getCountryCode()
                + ","
                + attribute.getPostCode()
                + ","
                + attribute.getTownName()
                + ","
                + attribute.getStreetName()
                + ","
                + attribute.getBuildingNumber();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }

        String[] address = dbData.split(",");

        return new Address(
                address[0], address[1], address[2], address[3], address[4]
        );
    }
}

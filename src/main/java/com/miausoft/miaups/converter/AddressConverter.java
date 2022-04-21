package com.miausoft.miaups.converter;

import com.miausoft.miaups.persistence.entities.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address attribute) {
        return attribute.getStreetName()
                + ","
                + attribute.getBuildingNumber()
                + ","
                + attribute.getPostCode()
                + ","
                + attribute.getTownName()
                + ","
                + attribute.getCountryCode();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        String[] address = dbData.split(",");

        return new Address(
                address[0], address[1], address[2], address[3], address[4]
        );
    }
}

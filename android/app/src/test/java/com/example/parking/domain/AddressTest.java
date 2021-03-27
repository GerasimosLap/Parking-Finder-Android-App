package com.example.parking.domain;

import com.example.parking.util.ZipCode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddressTest {
    private Address address;
    private String street;
    private String number;
    private ZipCode zipCode;
    @Before
    public void setup(){
        zipCode = new ZipCode();
        zipCode.setZip(18530);
        street="Vasilisis Sofias";
        number = "53";
        address = new Address();
        address.setStreet(street);
        address.setStreetNumber(number);
        address.setZipCode(zipCode);

    }
    @Test
    public void getStreetTest(){
        assertEquals(street,address.getStreet());
    }

    @Test
    public void getStreetNumberTest(){
        assertEquals(number,address.getStreetNumber());
    }

    @Test
    public void getZipCodeTest(){
        assertEquals(zipCode,address.getZipCode());
    }

}
package com.example.parking.domain;

import com.example.parking.util.ZipCode;

public class Address {
    private String street;
    private String streetNumber;
    private ZipCode zipCode;

    public Address(String street, String streetNumber, ZipCode zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public Address() {
        this.street = "";
        this.streetNumber = "";
        this.zipCode = new ZipCode();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}

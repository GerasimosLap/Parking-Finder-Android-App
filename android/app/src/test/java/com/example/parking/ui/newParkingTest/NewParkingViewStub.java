package com.example.parking.ui.newParkingTest;

import com.example.parking.ui.newParking.NewParkingView;
import com.example.parking.util.TimeRange;

import java.util.ArrayList;

public class NewParkingViewStub implements NewParkingView {
    private String street,streetno,zip,plate,credits,username,toast="";
    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getStreetNumber() {
        return streetno;
    }

    @Override
    public String getZipCode() {
        return zip;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public String getCredits() {
        return credits;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public TimeRange getTimeRange() {
        return new TimeRange(30);
    }

    @Override
    public void successfullyFinishActivity() {

    }

    public String getToast() {
        return toast;
    }
    @Override
    public void setSpinner(ArrayList<String> plates) {

    }

    @Override
    public void makeToast(String m) {
        toast=m;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

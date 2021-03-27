package com.example.parking.ui.showParkingSpaceTest;

import com.example.parking.ui.showParkingSpace.ShowParkingView;

public class ShowParkingSpaceViewStub implements ShowParkingView {
    String req,parked,plate,zip="";
    @Override
    public String getRequestingUser() {
        return req;
    }

    @Override
    public void setParkedUser(String parkedUsername) {
        parked=parkedUsername;
    }

    @Override
    public void setVehicle(String plate) {
        this.plate=plate;
    }

    @Override
    public void setAddress(String zip) {
        this.zip=zip;
    }

    @Override
    public String getParkedUsername() {
        return parked;
    }
}

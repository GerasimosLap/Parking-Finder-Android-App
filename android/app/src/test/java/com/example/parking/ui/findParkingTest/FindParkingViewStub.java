package com.example.parking.ui.findParkingTest;
import android.widget.Button;

import com.example.parking.domain.ParkingSpace;
import com.example.parking.ui.findParking.FindParkingView;
import com.example.parking.util.TimeRange;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FindParkingViewStub implements FindParkingView
{
    String zipcode,toast;
    LocalDateTime expectedArrivalTime;

    public FindParkingViewStub()
    {
        zipcode = "12211";
        expectedArrivalTime = LocalDateTime.now().plusMinutes(30);
    }

    @Override
    public String getZip() {
        return zipcode;
    }

    @Override
    public void makeToast(String m) {
        toast = m;
    }


    @Override
    public void setErrorToZip(String error) {
        zipcode = error;
    }

    @Override
    public void showParkingSpace(ArrayList<ParkingSpace> DaoParkingSpace) {
        for (ParkingSpace p : DaoParkingSpace){
            System.out.println(p);
        }
    }

    @Override
    public String getUserName() {
        return "test name";
    }

    @Override
    public LocalDateTime getExpectedArrivalDateTime()
    {
        return expectedArrivalTime;
    }
}

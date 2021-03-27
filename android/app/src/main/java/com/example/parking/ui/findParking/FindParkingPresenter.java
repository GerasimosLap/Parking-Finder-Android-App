package com.example.parking.ui.findParking;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.dao.ParkingSpaceDAO;
import com.example.parking.domain.Address;
import com.example.parking.domain.ParkingRequest;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.util.ZipCode;

import java.util.ArrayList;

public class FindParkingPresenter
{
    FindParkingView view;
    ParkingSpaceDAO parkingSpaceDAO;


    public FindParkingPresenter(FindParkingView view, ParkingSpaceDAO parkingSpaceDAO)
    {
        this.view = view;
        this.parkingSpaceDAO = parkingSpaceDAO;
    }

    /**
     * Βρίσκει και εμφανίζει τις έγκυρες θέσεις στάθμευσης
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void find()
    {
        if (validateZip()) {
            ArrayList<ParkingSpace> results = new ParkingRequest().FindParking((ArrayList<ParkingSpace>) parkingSpaceDAO.findAllAvailable(), new Address("", "", new ZipCode(Integer.valueOf(view.getZip()))), 30, view.getExpectedArrivalDateTime());
            view.showParkingSpace(results);
        }
    }

    /**
     * @return {@code true} αν ο δοθέν Τ.Κ. είναι έγκυρος
     */
    public boolean validateZip()
    {
        String zip = view.getZip();
        if (zip.isEmpty()) {
            view.setErrorToZip("ZipCode cannot be empty");
            return false;
        } else if (zip.length() != 5) {
            view.setErrorToZip("ZipCode must be 5 digits");
            return false;
        } else if (!zip.matches("\\d+")) {
            view.setErrorToZip("ZipCode must be a number");
            return false;
        } else {
            view.setErrorToZip(null);
            return true;
        }
    }

}




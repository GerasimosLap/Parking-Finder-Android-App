package com.example.parking.ui.newParking;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.dao.ParkingSpaceDAO;
import com.example.parking.dao.UserDAO;
import com.example.parking.domain.Address;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;
import com.example.parking.util.Credits;
import com.example.parking.util.ZipCode;

import java.util.ArrayList;
import java.util.Date;

public class NewParkingPresenter {
    NewParkingView view;
    UserDAO userDAO;
    ParkingSpaceDAO parkingDAO;
    User u;

    public NewParkingPresenter(NewParkingView view, UserDAO userDAO, ParkingSpaceDAO parkingDAO) {
        this.view = view;
        this.userDAO = userDAO;
        this.parkingDAO = parkingDAO;
        u = userDAO.find(view.getUsername());
        setPlate();
    }

    /**
     * Δημιουργεί καινούρια θέση στάθμευσης με τα συμπληρωμένα στοιχεία και την αποθηκεύει
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add() {
        ParkingSpace p = new ParkingSpace(new Address(view.getStreet(), view.getStreetNumber(), new ZipCode(Integer.valueOf(view.getZipCode()))), true, new Credits(Integer.valueOf(view.getCredits())), view.getTimeRange(), new Date(), u, view.getPlate());
        if (parkingDAO.find(p) == null) {
            parkingDAO.save(p);
            view.makeToast("Parking space added!");
            view.successfullyFinishActivity();
        }else{
            view.makeToast("Parking space already exists!");
        }
    }

    /**
     * Βάζει τα plates στον spinner.
     */
    void setPlate(){
        ArrayList<String> pl = new ArrayList<>();
        for(Vehicle v: u.getVehicles()){
            pl.add(v.getPlate());
        }
        view.setSpinner(pl);
    }
}

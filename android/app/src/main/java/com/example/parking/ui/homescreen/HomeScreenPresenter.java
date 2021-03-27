package com.example.parking.ui.homescreen;

import com.example.parking.dao.UserDAO;

public class HomeScreenPresenter {
    HomeScreenView view;
    UserDAO dao;
    public HomeScreenPresenter(HomeScreenView view,UserDAO dao){
        this.view=view;
        this.dao=dao;
    }

    /**
     * O χρήστης μεταφέρεται στο NewParkingSpace activity
     * αφού πρώτα ελεγχθεί ότι ο χρήστης έχει αμάξι
     */
    public void spaceIntent(){
        if(checkForVehicle()){
            view.spaceIntent();
        }else{
            view.makeToast("Please add a vehicle to continue.");
        }
    }


    /**
     * O χρήστης μεταφέρεται στο FindParking activity
     * αφού πρώτα ελεγχθεί ότι ο χρήστης έχει αμάξι
     */
    public void requestIntent(){
        if(checkForVehicle()){
            view.requestIntent();
        }else{
            view.makeToast("Please add a vehicle to continue.");
        }
    }

    /**
     * O χρήστης μεταφέρεται στο UserProfile activity
     */
    public void profileIntent(){
        view.profileIntent();
    }

    /**
     * O χρήστης μεταφέρεται στο Notifications activity
     */
    public void notificationIntent(){
        view.notificationIntent();
    }

    public boolean checkForVehicle(){
        int vehicles = dao.find(view.getUserName()).getVehicles().size();
        return vehicles != 0;
    }
}

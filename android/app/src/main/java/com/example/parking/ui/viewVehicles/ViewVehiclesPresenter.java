package com.example.parking.ui.viewVehicles;

import com.example.parking.dao.UserDAO;
import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;

import java.util.ArrayList;

public class ViewVehiclesPresenter {
    private ViewVehiclesView view;
    private User user;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param dao Ένα instance του user
     */
    public ViewVehiclesPresenter(ViewVehiclesView view,UserDAO dao){
        this.view=view;
        this.user = dao.find(view.getUserName());
        System.out.println(view.getUserName());
        showVehicles();
    }

    /**
     * Εμφανίζει τα vehicles ενος user.
     */
    public void showVehicles(){
        view.showVehicles(user.getVehicles());
    }

    /**
     * Εμφανίζει τα στοιχεία ενός vehicle.
     * @param vehicle Τα vehicles που επιλέχθηκε.
     */
    public void viewOneVehicle(Vehicle vehicle){
        view.viewOneVehicle(vehicle);
    }



}

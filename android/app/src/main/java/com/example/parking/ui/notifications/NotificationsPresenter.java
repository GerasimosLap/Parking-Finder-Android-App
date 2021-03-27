package com.example.parking.ui.notifications;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.dao.ParkingRequestDAO;
import com.example.parking.dao.UserDAO;
import com.example.parking.domain.ParkingRequest;
import com.example.parking.domain.Rating;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.util.Pin;

import java.util.ArrayList;

public class NotificationsPresenter {
    NotificationsView view;
    ParkingRequestDAO dao;
    UserDAO users;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationsPresenter(NotificationsView view, ParkingRequestDAO dao, UserDAO users){
        this.view=view;
        this.dao=dao;
        this.users=users;
        String username = view.getUserName();
        ArrayList<ParkingRequest> all = new ArrayList<>();

        for(ParkingRequest request: dao.findAll()){
            if(request.getRequestingUser().getUsername().equals(username) || request.getParkingSpace().getParkedUser().getUsername().equals(username) ){
                all.add(request);
            }
        }
        view.showNotifications(all,username);
    }

    /**
     * Ελέγχει εαν το πιν είναι σωστό, αν ναι τοτε η συναλλαγή ολοκληρώνεται.
     * @param request Το αίτημα.
     * @param pin Το πιν.
     * @return Επιστρέφει true αν δούλεψε και false αν όχι.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean validateParking(ParkingRequest request, Pin pin){
        int results = dao.find(request).validateParking(pin);


       if(results==1){
            dao.delete(request);

           view.makeToast("Transaction complete");
            return true;
       }else{
           view.makeToast("Wrong pin.");
           return false;
       }
    }

    /**
     * Αποδέχεται το αίτημα και δημιουργείται ένα τυχαίο pin.
     * @param request Το αίτημα προς αποδοχή.
     * @return Επιστρέφει το pin.
     */
    public int approveRequest(ParkingRequest request){
        int pin=Pin.createPin();
        request.setPin(new Pin(pin));
        view.makeToast("Pin generated");
        return pin;
    }

    /**
     * Απορρίπτει το αίτημα και θέτει την ημερομηνία ως null για να σηματοδοτήσει την απόρριψή του.
     * @param request Το αίτημα.
     */
    public void denyRequest(ParkingRequest request){
        request.setDate(null);
        view.makeToast("Request denied");
    }

    /**
     * Δημιουργεί μία αρνητική κριτική προς τον σταθμευμένο χρήστη καθώς δεν ήταν εκεί.
     * @param request Το αίτημα
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createRating(ParkingRequest request){
        dao.delete(request);
        Rating rating = new Rating(1,"Not there",request.getParkingSpace().getParkedUser().getUsername(),request.getRequestingUser().getUsername());
        MemoryInitializer.getRatingDAO().save(rating);
    }
}

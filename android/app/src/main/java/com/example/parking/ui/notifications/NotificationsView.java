package com.example.parking.ui.notifications;

import com.example.parking.domain.ParkingRequest;

import java.util.ArrayList;

public interface NotificationsView {
    String getUserName();

    /**
     * Εμφανίζει τα δυναμικά παραγώμενα κουμπιά.
     * @param DaoParkingSpace Τα εκκρεμή parking request
     * @param notif Το περιεχόμενο που θα εμφανιστεί
     */
    void showNotifications(ArrayList<ParkingRequest> DaoParkingSpace, String notif);


    /**
     * Εμφανίζει ένα Toast.
     * @param s Το περιεχόμενο που θα εμφανιστεί
     */
    void makeToast(String s);
}

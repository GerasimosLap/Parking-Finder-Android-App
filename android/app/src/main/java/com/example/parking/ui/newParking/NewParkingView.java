package com.example.parking.ui.newParking;

import com.example.parking.util.TimeRange;

import java.util.ArrayList;

public interface NewParkingView {
    /**
     * @return Όνομα οδού της θέσης parking
     */
    String getStreet();
    /**
     * @return Αριθμό της θέσης parking
     */
    String getStreetNumber();
    /**
     * @return Τ.Κ. της θέσης parking
     */
    String getZipCode();
    /**
     * @return Πινακίδα του αμαξιού της θέσης parking
     */
    String getPlate();
    /**
     * @return Απαιτούμενες πιστωτικές μονάδες της θέσης parking
     */
    String getCredits();
    /**
     * @return Όνομα χρήστη κατόχου της θέσης parking
     */
    String getUsername();

    /**
     * @return Χρονική περίοδος που θα είναι διαθέσιμη η θέση
     */
    TimeRange getTimeRange();



    void successfullyFinishActivity();
    void setSpinner(ArrayList<String> plates);
    void makeToast(String m);
}

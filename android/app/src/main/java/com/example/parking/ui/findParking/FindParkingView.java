package com.example.parking.ui.findParking;

import com.example.parking.domain.ParkingSpace;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface FindParkingView {
    /**
     * Επιστρέφει τον Ταχυδρομικό Κώδικα
     * @return Ο Τ.Κ.
     */
    String getZip();

    /**
     * Επιστρέφει το όνομα του χρήστη.
     * @return Το όνομα χρήστη
     */
    String getUserName();


    /**
     * @return Η εκτιμώμενη χρονική στιγμή που θα καταυθάσει στο στημείο ο χρήστης
     */
    LocalDateTime getExpectedArrivalDateTime();
    /**
     * Εμφανίζει όλες τις έγγυρες θέσεις parking
     * @param DaoParkingSpace Λίστα με τις έγγυρες θέσεις parking
     */
    void showParkingSpace(ArrayList<ParkingSpace> DaoParkingSpace);

    /**
     * Φτιάχνει ένα Toast.
     * @param m Το περιεχόμενο του Toast.
     */
    void makeToast(String m);

    /**
     * @param error Το μήνυμα λάθους που θα εμφανιστεί στο πεδίο του zip
     */
    void setErrorToZip(String error);
}

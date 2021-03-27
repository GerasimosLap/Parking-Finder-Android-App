package com.example.parking.ui.homescreen;

public interface HomeScreenView {
    /**
     * O χρήστης μεταφέρεται στο NewParkingSpace activity
     */
    void spaceIntent();

    /**
     * O χρήστης μεταφέρεται στο FindParking activity
     */
    void requestIntent();

    /**
     * O χρήστης μεταφέρεται στο UserProfile activity
     */
    void profileIntent();

    /**
     * O χρήστης μεταφέρεται στο Notifications activity
     */
    void notificationIntent();

    /**
     * Επιστρέφει το όνομα του χρήστη.
     * @return Το όνομα.
     */
    String getUserName();

    /**
     * Εμφανίζει ένα Toast
     * @param m Το περιεχόμενο του toast
     */
    void makeToast(String m);
}

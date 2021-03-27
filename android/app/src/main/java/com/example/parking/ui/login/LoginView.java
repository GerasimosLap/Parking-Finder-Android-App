package com.example.parking.ui.login;

public interface LoginView {
    String getUsername();
    String getPassword();

    /**
     * Δημιουργεί το νέο Intent που πάει τον logged in
     * χρήστη στην αρχική του οθόνη.
     */
    void moveOn();

    /**
     * Εμφανίζει ένα Toast.
     * @param text Το περιεχόμενο που θα εμφανιστεί
     */

    void createToast(String text);


    /**
     * Μεταφέρει τον χρήστη στο activity SignUp
     * μόλις πατήσει το κουμπί sign up.
     */
    void signup();
}

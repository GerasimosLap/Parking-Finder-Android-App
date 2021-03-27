package com.example.parking.ui.signup;

public interface SignUpView {
    String getName();
    String getSurname();
    String getPhone();
    String getEmail();
    String getUsername();
    String getPassword();
    String getStrN();
    String getZipCode();
    String getStreet();

    /**
     * Εμφανίζει ένα Toast.
     * @param text Το περιεχόμενο που θα εμφανιστεί
     */

    void makeToast(String text);

    /**
     * Εμφανίζει ένα error δίπλα απο συγκεκριμένο πεδίο.
     * @param EditText Το όνομα του κουμπιού στο οποίο θα εμφανιστεί το σφάλμα
     * @param error Το περιεχόμενο του σφάλματος που θα εμφανιστεί
     */
    void setError(String EditText,String error);

    /**
     * Το μήνυμα πoυ εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String message);
}

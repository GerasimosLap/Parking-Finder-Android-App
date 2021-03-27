package com.example.parking.ui.viewUser;

public interface UserProfileView {
    void setCredits(int credits);
    int getCredits();
    String getFirstName();
    void setFirstName(String value);
    String getLastName();
    void setLastName(String value);
    String getEmail();
    void setEmail(String value);
    String getZip();
    void setZip(String value);
    String getStreet();
    void setStreet(String value);
    String getStreetNo();
    void setStreetNo(String value);
    String getPhone();
    void setPhone(String value);
    String getUsername();
    void setIntentUsername(String username);
    String getIntentUsername ();

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String message);

    String getErrorTitle();
    String getFinishMessage();
    String getErrorMessage();

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showErrorMessage(String title, String message);
}

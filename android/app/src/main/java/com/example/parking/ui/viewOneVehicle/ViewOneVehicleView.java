package com.example.parking.ui.viewOneVehicle;

import com.example.parking.util.Colour;

public interface ViewOneVehicleView {
    void setBrand(String value);
    String getBrand();

    void setModel(String value);
    String getModel();

    void setPlate(String value);
    String getPlateText();

    void setLength(int value);
    int getLength();

    void setText(String value);
    String getText();

    Colour getColour();
    void setColour(String color);
    String getUserName();

    String getPlate();

        void setInvisibility();

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param value Το μήνυμα που θα εμφανίσει
     */
    void successfullyFinishActivity(String value);


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

    void setIntentUsername(String username);
    String getIntentUsername ();


    void setIntentPlate(String plate);
    String getIntentPlate ();
}

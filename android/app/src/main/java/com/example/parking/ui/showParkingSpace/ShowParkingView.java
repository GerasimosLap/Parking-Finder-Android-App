package com.example.parking.ui.showParkingSpace;

public interface ShowParkingView {
    /**
     * @return Το username του χρήστη που έκανε το αίτημα ή null αν δεν υπάρχει
     */
    String getRequestingUser();

    /**
     * @param parkedUsername Το username του παρκαρισμένου χρήστη
     */
    void setParkedUser(String parkedUsername);

    /**
     * @param plate Η πινακίδα του αμαξιού
     */
    void setVehicle(String plate);

    /**
     * @param zip Ο Τ.Κ. της θέσης parking
     */
    void setAddress(String zip);

    /**
     * @return Το username του παρκαρισμένου χρήστη
     */
    String getParkedUsername();


}

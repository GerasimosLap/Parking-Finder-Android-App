package com.example.parking.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.util.Pin;
import com.example.parking.util.TimeRange;
import com.example.parking.util.ZipCode;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class ParkingRequest{
    private TimeRange date;//date einai h wra pou ypologizei o requestingUser na ftasei sto parking
    private Pin pin;
    private User requestingUser;
    private ParkingSpace parkingSpace;

    public ParkingRequest(TimeRange date, Pin pin, User requestingUser,ParkingSpace parkingSpace) {
        this.date = date;
        this.pin = pin;
        this.requestingUser = requestingUser;
        this.parkingSpace=parkingSpace;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ParkingRequest() {
        this.date = new TimeRange(0);
        this.pin = new Pin();
        this.requestingUser = new User();
        this.parkingSpace = new ParkingSpace();
    }


    public TimeRange getDate() {
        return date;
    }

    public void setDate(TimeRange date) {
        this.date = date;
    }

    public Pin getPin() {
        return pin;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    /**
     * Βρίσκει parking σύμφωνα με τον Τ.Κ. και την ώρα. Αν δεν υπάρχει κοντά ψάχνει τους κοντινούς Τ.Κ.
     * @param parkingSpaces Λίστα με ολα τα διαθέσιμα {@code ParkingSpace} αντικείμενα
     * @param difference Μέγιστη "απόσταση" για ψάξιμο, αναπαραστόμενη ως διαφορά των Zip Codes
     * @return Ο κατάλογος των αντικειμένων
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<ParkingSpace> FindParking(ArrayList<ParkingSpace> parkingSpaces, Address address,int difference, LocalDateTime expectedArrival){
        ArrayList<ParkingSpace> list = new ArrayList<>();
        ZipCode zip = address.getZipCode();
        for (ParkingSpace parking : parkingSpaces) {
                ZipCode currentZip = parking.getAddress().getZipCode();
            if (Math.abs(zip.getZip() - currentZip.getZip()) <= difference) {
                if(parking.getTimeRange().containsDateTime(expectedArrival)){
                   list.add(parking);
                }
            }
        }
        return list;
    }



    /**
     * Είναι υπεύθυνο για την διεκπεραίωση της διαδικασίας ανταλλαγής θέσης
     * καθώς και την μεταφορά μονάδων από τον ενδιαφερόμενο στον
     * σταθμευμένο χρήστη.
     * @param pin Το pin
     * @return Επιστρέφει το resultCode αναλογά της έκβασης της συνάρτησης
     * resultCode= {    1:  το pin ειναι σωστο και εχει εγκριθει η συναλλαγη, ta credits ανταλλαζονται μεταξυ των συνδιαλλασσομενων
     *                  2:  το pin ειναι λαθος και η συναρτηση σταματα
     *              }
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int validateParking(Pin pin){

        if(pin.getPin()==getPin().getPin()){//get pin of class pin from parkingRequest pin
            getParkingSpace().makeParkingUnavailable();

            calculatePenalty();

            getRequestingUser().getCredits().removeCredits(getParkingSpace().getPrice().getPoints());


            getParkingSpace().getParkedUser().getCredits().addCredits(getParkingSpace().getPrice().getPoints());

            return 1;
        }
        return 2;
    }

    /**
     * Είναι υπεύθυνο για την διεκπεραίωση της διαδικασίας επιβολής ποινής
     * καθώς και την μεταφορά μονάδων από τον ενδιαφερόμενο στον
     * σταθμευμένο χρήστη.
     * resultCode= {    1:  το pin ειναι σωστο και εχει εγκριθει η συναλλαγη, ta credits ανταλλαζονται μεταξυ των συνδιαλλασσομενων
     *                  2:  το pin ειναι λαθος και η συναρτηση σταματα
     *              }
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    void calculatePenalty(){
        TimeRange currentTime = new TimeRange(0);
        currentTime.setFrom(date.getTo());
        System.out.println(currentTime.toString());
        long minutesDif = currentTime.getDifference();
        int mod = (int)minutesDif%3;
        int penalty = mod;
        System.out.println(minutesDif);
        if(minutesDif>=30){
            penalty+=2;
            requestingUser.setPenalty(penalty);
        }
    }

    @Override
    public String toString() {
        return "ParkingRequest{" +
                "date=" + date +
                ", pin=" + pin +
                ", requestingUser=" + requestingUser +
                ", parkingSpace=" + parkingSpace +
                '}';
    }
}

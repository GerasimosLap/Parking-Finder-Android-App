package com.example.parking.domain;


import com.example.parking.util.Credits;

import java.util.ArrayList;

public class User {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Credits credits;
    private Address address;
    private ArrayList<Rating> rating;
    private ArrayList<Vehicle> vehicles;

    public User(String name, String surname, String phone, String email, String username,
                String password, Credits credits, Address address,
                ArrayList<Rating> rating, ArrayList<Vehicle> vehicles) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.credits = credits;
        this.address = address;
        this.rating = new ArrayList<>(rating);
        this.vehicles = new ArrayList<>(vehicles);
    }
    public User(){
        this.name = "";
        this.surname = "";
        this.phone = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.credits = new Credits(10 );
        this.address = new Address();
        this.rating = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Credits getCredits() {
        return credits;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRating(ArrayList<Rating> rating) {
        this.rating = rating;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Rating> getRating() {
        return rating;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addCredits(Credits credits){
        getCredits().addCredits(credits.getPoints());
    }

    public void removeCredits(Credits credits){
        getCredits().removeCredits(credits.getPoints());
    }

    /**
     *
     * @param plate Το plate ενός vehicle που ψάχνει.
     * @return Επιστρέφει το vehicle αν το βρει, ή ενα νέο vehicle.
     */
    public Vehicle getVehicle(String plate) {
        if (getVehicles().size() > 0) {
            for (Vehicle vehicle : getVehicles()) {
                if (vehicle.getPlate().equals(plate)) {
                    return vehicle;
                }
            }
            return new Vehicle();
        }else{
            return new Vehicle();
        }
    }

    /**
     * Αν το penalty περισσότερο απο τα credits που έχει τότε του μηδενίζονται τα credits, αλλιώς αφαιρείται.
     * @param penalty Ποινή αν αργήσει
     */
    public void setPenalty(int penalty){
        if(penalty>getCredits().getPoints()){
            credits.setPoints(0);
        }else{
            credits.setPoints(getCredits().getPoints()-penalty);
        }
    }

    public void addRating(Rating rating){
        this.rating.add(rating);
    }

    public void removeRating(Rating rating){
        this.rating.remove(rating);
    }

    /**
     Προσθέτει ενα όχημα αν δεν υπάρχει ήδη στον χρήστη.
     @param vehicle το όχημα προς προσθήκη.
     */

     public void addVehicle(Vehicle vehicle){
        if(vehicles.size()>0) {
            for (Vehicle v : vehicles) {
                if (v.getPlate().equals(vehicle.getPlate())) {
                    return;
                }
            }
            this.vehicles.add(vehicle);
        }else{
            this.vehicles.add(vehicle);
        }

    }
    /**Αφαιρειί ένα όχημα (μόνο αν ο χρήστης έχει 2 και πάνω.
     *
     * @param vehicle το όχημα
     */
    public void removeVehicle(Vehicle vehicle){
        if(vehicles.size()>0){
            for (Vehicle v : vehicles) {
                if(vehicle.getPlate().equals(v.getPlate())){
                    this.vehicles.remove(v);
                    return;
                }
            }
        }
    }

    /**Υπολογίζει τον μέσο όρο αξιολογήσεων
     *
     * @return τον Μ.Ο. αξιολογήσεων.
     */
    public double calculateRating(){
        int currentRating = 0;
        for(Rating rate: rating){
            currentRating+=rate.getRatingScore();
        }
        return currentRating/rating.size();
    }




    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", credits=" + credits +
                ", address=" + address +
                ", rating=" + rating +
                '}';
    }
}

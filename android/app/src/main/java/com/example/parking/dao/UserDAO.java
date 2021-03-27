package com.example.parking.dao;


import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;

import java.util.ArrayList;

public interface UserDAO {

    /**
     * Αποθηκεύει έναν νέο χρήστη.
     * @param u Ο νέος χρήστης.
     */
    void save(User u);

    /**
     * Ελέγχει εάν τα στοιχεία του αιτούμενου χρήστη υπάρχουν στην βάση
     * @param username Το όνομα του χρήστη.
     * @param password Ο κωδικός πρόσβασης.
     * @return Ο χρήστης.
     */
    User login(String username,String password);

    /**
     * Ενημερώνει τα στοιχεία του χρήστη.
     * @param u Ο χρήστης.
     */
    void update(User u);

    /**
     * Διαγράφει τον χρήστη.
     * @param u Ο χρήστης.
     */
    void delete(User u);

    /**
     * Αναζητά το χρήστη με βάση το username του.
     * @param username Το username του χρήστη που αναζητείται.
     * @return Ο χρήστης που αναζητούμε.
     */
    User find(String username);

    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Όλοι οι χρήστες.
     */
    ArrayList<User> findAll();



    /**
     * Αναζητά για ένα συγκεκριμένο αμαξί το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param plate Οι πινακίδες του οχήματος.
     * @return Το όχημα.
     */
    Vehicle findVehicle(String username,String plate);

    /**
     * Ενημερώνει τα στοιχεία ενός συγκεκριμένου αμαξιού το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param vehicle Το όχημα.
     */
    void updateVehicle(String username,Vehicle vehicle);

    /**
     * Διαγράφει ένα συγκεκριμένο αμαξί το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param temp Το όχημα.
     */
    void deleteVehicle(String username,Vehicle temp);
}

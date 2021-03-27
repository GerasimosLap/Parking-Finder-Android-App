package com.example.parking.dao;

import com.example.parking.domain.ParkingSpace;

import java.util.List;

public interface ParkingSpaceDAO {

    /**
     * Αποθηκεύει ένα parking space
     * @param entity Το parking space προς αποθήκευση
     */
    void save(ParkingSpace entity);

    /**
     * Διαγράφει ένα parking space
     * @param entity Το parking space προς διαγραφή
     */
    void delete(ParkingSpace entity);

    /**
     * Βρίσκει όλα τα parking spaces
     * @return Επιστρέφει τα parking space
     */
    List<ParkingSpace> findAll();

    /**
     * Αναζητάει ένα parking space
     * @param parking Το parking space προς αναζήτηση
     * @return Επιστρέφει το parking, αν το βρει
     */
    ParkingSpace find(ParkingSpace parking);

    /**
     * Ψάχνει όλα τα διαθέσιμα parking spaces
     * @return Επιστρέφει όλα τα διαθέσιμα parking spaces
     */
    List <ParkingSpace> findAllAvailable();
}

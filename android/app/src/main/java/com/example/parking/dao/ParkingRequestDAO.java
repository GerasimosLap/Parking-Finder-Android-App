package com.example.parking.dao;


import com.example.parking.domain.ParkingRequest;

import java.util.List;

public interface ParkingRequestDAO {

    /**
     * Ψάχνει το newRequest στο DAO
     * @param newRequest ενα parking request
     * @return Επιστρέφει το parking request που βρήκε
     */
    ParkingRequest find(ParkingRequest newRequest);

    /**
     * Αποθηλεύει το entity.
     * @param entity Το parking request προς αποθήκευση
     */
    void save(ParkingRequest entity);

    /**
     * Διαγράφη το entity.
     * @param entity Το parking request προς διαγραφή
     */
    void delete(ParkingRequest entity);

    /**
     * Επιστρέφει όλα τα parking request
     */
    List<ParkingRequest> findAll();
}

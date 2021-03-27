package com.example.parking.memorydao;

import com.example.parking.dao.ParkingRequestDAO;
import com.example.parking.domain.ParkingRequest;

import java.util.ArrayList;
import java.util.List;

public class ParkingRequestDAOMemory implements ParkingRequestDAO{
    protected static ArrayList<ParkingRequest> requests = new ArrayList<ParkingRequest>();

    /**
     * Διαγράφη το entity.
     * @param request Το parking request προς διαγραφή
     */
    public void delete(ParkingRequest request) {
        requests.remove(request);
    }

    /**
     * Επιστρέφει όλα τα parking request
     */
    public List<ParkingRequest> findAll() {
        return new ArrayList<ParkingRequest>(requests);
    }

    /**
     * Αποθηλεύει το entity.
     * @param request Το parking request προς αποθήκευση
     */
    public void save(ParkingRequest request) {
        if (! requests.contains(request)) {
            requests.add(request);
        }
    }

    /**
     * Ψάχνει το newRequest στο DAO
     * @param newRequest ενα parking request
     * @return Επιστρέφει το parking request που βρήκε
     */
    public ParkingRequest find(ParkingRequest newRequest) {
        for(ParkingRequest request : requests) {
            if (request.equals(newRequest)) {
                return request;
            }
        }
        return null;
    }
}

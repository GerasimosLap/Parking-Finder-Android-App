package com.example.parking.memorydao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.dao.ParkingSpaceDAO;
import com.example.parking.domain.ParkingSpace;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceDAOMemory implements ParkingSpaceDAO {

    protected static ArrayList<ParkingSpace> parkingSpaces = new ArrayList<ParkingSpace>();

    /**
     * Αποθηκεύει ένα parking space
     * @param p Το parking space προς αποθήκευση
     */
    @Override
    public void save(ParkingSpace p){
        if(!parkingSpaces.contains(p)){
            parkingSpaces.add(p);
        }
    }

    /**
     * Διαγράφει ένα parking space
     * @param p Το parking space προς διαγραφή
     */
    @Override
    public void delete(ParkingSpace p){
        parkingSpaces.remove(p);
    }

    /**
     * Βρίσκει όλα τα parking spaces
     * @return Επιστρέφει τα parking space
     */
    @Override
    public List<ParkingSpace> findAll(){
        return new ArrayList<ParkingSpace> (parkingSpaces);
    }

    /**
     * Αναζητάει ένα parking space
     * @param parking Το parking space προς αναζήτηση
     * @return Επιστρέφει το parking, αν το βρει
     */
    @Override
    public ParkingSpace find(ParkingSpace parking){
        for(ParkingSpace p: parkingSpaces){
            if(p.equals(parking)){
                return p;
            }
        }
        return null;
    }

    /**
     * Ψάχνει όλα τα διαθέσιμα parking spaces
     * @return Επιστρέφει όλα τα διαθέσιμα parking spaces
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List <ParkingSpace> findAllAvailable(){
        List<ParkingSpace> allps = findAll();
        List<ParkingSpace> avps = new ArrayList<ParkingSpace>();
        for(ParkingSpace p: allps){
            if(p.getAvailability()){
                avps.add(p);
            }
        }
        return avps;
    }



}

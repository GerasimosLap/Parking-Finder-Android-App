package com.example.parking.memorydao;

import com.example.parking.dao.UserDAO;
import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;

import java.util.ArrayList;

public class UserDAOMemory implements UserDAO {

    protected static ArrayList<User> users = new ArrayList<>();


    /**
     * Αποθηκεύει έναν νέο χρήστη.
     * @param u Ο νέος χρήστης.
     */
    @Override
    public void save(User u){
        if(!users.contains(u)){
            users.add(u);
        }
    }

    /**
     * Ελέγχει εάν τα στοιχεία του αιτούμενου χρήστη υπάρχουν στην βάση
     * @param username Το όνομα του χρήστη.
     * @param password Ο κωδικός πρόσβασης.
     * @return Ο χρήστης.
     */
    @Override
    public User login(String username,String password){
        User currentUser = find(username);
        if(currentUser!=null){
            if(currentUser.getPassword().equals(password)) {
                return currentUser;
            }
            return null;
        }
        return null;
    }

    /**
     * Διαγράφει τον χρήστη.
     * @param u Ο χρήστης.
     */
    @Override
    public void delete(User u){
        users.remove(u);
    }

    /**
     * Ενημερώνει τα στοιχεία του χρήστη.
     * @param u Ο χρήστης.
     */
    @Override
    public void update(User u){
        for(int i=0; i<users.size();i++){
            if(u.getUsername().equals(users.get(i).getUsername())){
                users.set(i,u);
            }
        }
    }


    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Όλοι οι χρήστες.
     */
    public ArrayList<User> findAll(){
        return users;
    }


    /**
     * Αναζητά το χρήστη με βάση το username του.
     * @param username Το username του χρήστη που αναζητείται.
     * @return Ο χρήστης που αναζητούμε.
     */
    @Override
    public User find(String username){
        for(User u: users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    /**
     * Αναζητά για ένα συγκεκριμένο αμαξί το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param plate Οι πινακίδες του οχήματος.
     * @return Το όχημα.
     */
    @Override
    public Vehicle findVehicle(String username,String plate){
        for(User u: users){
            if(u.getUsername().equals(username)){
                for(Vehicle vehicle:u.getVehicles()){
                    if(vehicle.getPlate().equals(plate)){
                        return vehicle;
                    }
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Διαγράφει ένα συγκεκριμένο αμαξί το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param temp Το όχημα.
     */
    @Override
    public void deleteVehicle(String username,Vehicle temp){
        for(User u: users){
            if(u.getUsername().equals(username)){
                for(int i=0;i<u.getVehicles().size();i++){
                    if(u.getVehicles().get(i).getPlate().equals(temp.getPlate())){
                        u.getVehicles().remove(temp);
                    }
                }
            }
        }
    }

    /**
     * Ενημερώνει τα στοιχεία ενός συγκεκριμένου αμαξιού το οποίο γνωριζούμε οτι ανήκει στον χρήστη username
     * @param username Το όνομα του χρήστη.
     * @param temp Το όχημα.
     */
    @Override
    public void updateVehicle(String username,Vehicle temp){
        for(User u: users){
            if(u.getUsername().equals(username)){
                for(int i=0;i<u.getVehicles().size();i++){
                    if(u.getVehicles().get(i).getPlate().equals(temp.getPlate())){
                        u.getVehicles().set(i,temp);
                        return;
                    }
                }
                u.getVehicles().add(temp);
            }
        }
    }


}

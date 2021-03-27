package com.example.parking.memorydao;

import com.example.parking.dao.RatingDAO;
import com.example.parking.domain.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingDAOMemory implements RatingDAO {

    protected static ArrayList<Rating> ratings = new ArrayList<Rating>();

    /**
     * Αποθηκεύει ένα νέο Rating.
     * @param r το Rating που θα αποθηκευτεί
     */
    @Override
    public void save(Rating r){
        ratings.add(r);
    }



    /**
     * Επιστρέφει όλα τις αξιολογήσεις που αφορούν έναν συγκεκριμένο χρήστη.
     * @param username Το όνομα του χρήστη
     * @return Οι αξιολογήσεις
     */
    public List<Rating> findAllOfUser(String username){
        List<Rating> ratedUser=new ArrayList<>();
        for(Rating r: ratings){
            if(r.getRatedUsername().equals(username)){
                ratedUser.add(r);
            }
        }
        return  ratedUser;

    }

    /**
     * Διαγράφει μία συγκεκριμένη αξιολόγηση.
     * @param p Η αξιολόγηση που θα διαγραφτεί.
     */
    @Override
    public void delete(Rating p){
        ratings.remove(p);
    }

    /**
     * Επιστρέφει όλες τις αξιολογήσεις που έχουν γίνει.
     * @return Όλες οι αξιολογήσεις
     */
    public List<Rating> findAll(){
        return ratings;
    }


    /**
     * Επιστρέφει το μέσο όρο των αξιολογήσεων που αφορούν ένα συγκεκριμένο χρήστη.
     * @param username Το όνομα του χρήστη
     * @return Ο μέσος όρος όλων των αξιολογήσεων του χρήστη.
     */
    public double calculateStats(String username){
        int size=0;
        int addition=0;
        for(Rating r: ratings){
            if(r.getRatedUsername().equals(username)){
                size++;
                addition+=r.getRatingScore();
            }
        }
        if(size!=0){
            return addition/size;
        }
        return 0.0;
    }



}

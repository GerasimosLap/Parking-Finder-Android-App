package com.example.parking.dao;


import com.example.parking.domain.Rating;

import java.util.List;

public interface RatingDAO {

    /**
     * Αποθηκεύει ένα νέο Rating.
     * @param r το Rating που θα αποθηκευτεί
     */
    void save(Rating r);

    /**
     * Επιστρέφει όλα τις αξιολογήσεις που αφορούν έναν συγκεκριμένο χρήστη.
     * @param username Το όνομα του χρήστη
     * @return Οι αξιολογήσεις
     */
    List<Rating> findAllOfUser(String username);

    /**
     * Επιστρέφει όλες τις αξιολογήσεις που έχουν γίνει.
     * @return Όλες οι αξιολογήσεις
     */
    List<Rating> findAll();

    /**
     * Διαγράφει μία συγκεκριμένη αξιολόγηση.
     * @param r Η αξιολόγηση που θα διαγραφτεί.
     */
    void delete(Rating r);

    /**
     * Επιστρέφει το μέσο όρο των αξιολογήσεων που αφορούν ένα συγκεκριμένο χρήστη.
     * @param username Το όνομα του χρήστη
     * @return Ο μέσος όρος όλων των αξιολογήσεων του χρήστη.
     */
    double calculateStats(String username);

}

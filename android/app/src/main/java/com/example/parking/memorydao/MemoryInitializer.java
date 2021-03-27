package com.example.parking.memorydao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.dao.ParkingRequestDAO;
import com.example.parking.dao.ParkingSpaceDAO;
import com.example.parking.dao.RatingDAO;
import com.example.parking.dao.UserDAO;
import com.example.parking.domain.Address;
import com.example.parking.domain.ParkingRequest;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.domain.Rating;
import com.example.parking.domain.User;
import com.example.parking.domain.Vehicle;
import com.example.parking.util.Colour;
import com.example.parking.util.Credits;
import com.example.parking.util.Pin;
import com.example.parking.util.TimeRange;
import com.example.parking.util.ZipCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class MemoryInitializer {
    private static ParkingRequestDAO PRDao = new ParkingRequestDAOMemory();
    private static ParkingSpaceDAO PSDao = new ParkingSpaceDAOMemory();
    private static UserDAO UDao = new UserDAOMemory();
    private static RatingDAO RDao = new RatingDAOMemory();

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected static void eraseData() {
        ArrayList<User> users = new ArrayList<>(getUserDAO().findAll());
        for(User user : users) {
            getUserDAO().delete(user);
        }

        ArrayList<ParkingRequest> parkingRequests = new ArrayList<>(getRequestDAO().findAll());
        for(ParkingRequest parkingRequest : parkingRequests) {
            getRequestDAO().delete(parkingRequest);
        }

        ArrayList<ParkingSpace> parkingSpaces = new ArrayList<>(getParkingDAO().findAll());
        for(ParkingSpace parkingSpace : parkingSpaces) {
            getParkingDAO().delete(parkingSpace);
        }

        ArrayList<Rating> ratings = new ArrayList<>(getRatingDAO().findAll());
        for(Rating r : ratings) {
            getRatingDAO().delete(r);
        }
    }

    /**
     * Προετοιμάζει τα δεδομένα
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void prepareData(){
        eraseData();

        //Init addresses
        Address add1 = new Address("Elm St.","69", new ZipCode(15125));
        Address add2 = new Address("Wall St.","23", new ZipCode(71310));
        Address add3 = new Address("Baker St.","158", new ZipCode(15145));
        Address add4 = new Address("Antoniadou St.","510", new ZipCode(16715));
        Address add5 = new Address("Derigny St.","53", new ZipCode(15115));



        //Init users3
        User user1= new User ("John", "Snow", "6980808080", "johnsnow@gmail.com", "IknowNothing","valarmorgulis",new Credits(15), add1, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        User user2= new User ("Walter", "White", "6941051051", "walterwhite@gmail.com", "GoodGuyWalt","saymyname",new Credits(20), add2, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        User user3= new User ("Jax", "Teller", "6912515918", "jaxteller@gmail.com", "JaxTellerHarley","jaxtellerdidnothingwrong",new Credits(12), add3, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        User user4= new User ("Harry", "Potter", "6991850231", "harrypotter@gmail.com", "ok","2",new Credits(6), add4, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        User user5= new User ("Luke", "Skywalker", "6958285692", "lukeskywalker@gmail.com", "WhoIsMyFather","iamyourfather",new Credits(9), add5, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        User user6= new User ("Luke", "Skywalker", "6958285692", "lukeskywalker@gmail.com", "ffff","ffff",new Credits(9), add5, new ArrayList<Rating>(),new ArrayList<Vehicle>());

        //vehicle ex vi is for user i vii is also for user i...viiii is for user i
        Vehicle v1 = new Vehicle(Colour.White,451,"Medium Size car, fits most places","IEH1234","A3","Audi");
        Vehicle v2 = new Vehicle(Colour.Green,463,"Medium SUV Car", "APK1551","2004 Aztek","Pontiac" );
        Vehicle v3 = new Vehicle(Colour.Black,324,"Medium Bike", "AZE9152","2003 Dyna Super Glide Sport with custom T-bars","Harley Davidson");
        Vehicle v4 = new Vehicle(Colour.Golden,490,"Large SUV", "MEA6157","Escalade","Cadillac");
        Vehicle v5 = new Vehicle(Colour.Blue,441,"Small SUV", "IZA6015","Escape","Ford");
        Vehicle v55 = new Vehicle(Colour.Black,499,"Space Battle Station", "ZOE5555","Death Star","Galactic Empire");

        user1.addVehicle(v1);
        user2.addVehicle(v2);
        user3.addVehicle(v3);
        user4.addVehicle(v4);
        user5.addVehicle(v5);
        user5.addVehicle(v55);

        getUserDAO().save(user1);
        getUserDAO().save(user2);
        getUserDAO().save(user3);
        getUserDAO().save(user4);
        getUserDAO().save(user5);
        getUserDAO().save(user6);

        ParkingSpace p1 = new ParkingSpace(add1,true,new Credits(10),new TimeRange(LocalDateTime.now(),30),new Date(),user1,"IEH1234");
        ParkingSpace p2 = new ParkingSpace(add2,true,new Credits(15),new TimeRange(LocalDateTime.now(),30),new Date(),user2,"APK1551");
        ParkingSpace p3 = new ParkingSpace(add3,true,new Credits(8),new TimeRange(LocalDateTime.now(),30),new Date(),user3,"AZE9152");
        ParkingSpace p4 = new ParkingSpace(add4,true,new Credits(3),new TimeRange(LocalDateTime.now(),30),new Date(),user4,"MEA6157");
        ParkingSpace p5 = new ParkingSpace(add5,true,new Credits(4),new TimeRange(LocalDateTime.now(),30),new Date(),user5,"IZA6015");

        getParkingDAO().save(p1);
        getParkingDAO().save(p2);
        getParkingDAO().save(p3);
        getParkingDAO().save(p4);
        getParkingDAO().save(p5);

        TimeRange time = new TimeRange(LocalDateTime.now(),0);
        time.addMinutes(time.getTo(),30);

        ParkingRequest pr0 = new ParkingRequest(time,null,user1,p4);
        ParkingRequest pr1 = new ParkingRequest(time,null,user3,p4);
        ParkingRequest pr2 = new ParkingRequest(time,new Pin(5555),user2,p4);
        ParkingRequest pr7 = new ParkingRequest(time,new Pin(5555),user5,p4);
        ParkingRequest pr3 = new ParkingRequest(time,new Pin(4689),user3,p1);
        ParkingRequest pr4 = new ParkingRequest(time,new Pin(9268),user4,p5);
        ParkingRequest pr5 = new ParkingRequest(time,new Pin(1235),user5,p1);

        getRequestDAO().save(pr7);
        getRequestDAO().save(pr0);
        getRequestDAO().save(pr1);
        getRequestDAO().save(pr2);
        getRequestDAO().save(pr3);
        getRequestDAO().save(pr4);
        getRequestDAO().save(pr5);

        Rating rating1 = new Rating();
        rating1.setRatingScore(5);
        rating1.setComment("Thank you for the exchange!");
        rating1.setRatedUsername("IknowNothing");
        rating1.setRatingUsername("GoodGuyWalt");
        getRatingDAO().save(rating1);

        Rating rating2 = new Rating();
        rating2.setRatingScore(5);
        rating2.setComment("He was 5 minutes late, but he said sorry!");
        rating2.setRatedUsername("JaxTellerHarley");
        rating2.setRatingUsername("IknowNothing");
        getRatingDAO().save(rating2);

        Rating rating3 = new Rating();
        rating3.setRatingScore(1);
        rating3.setComment("She never came!");
        rating3.setRatedUsername("JaxTellerHarley");
        rating3.setRatingUsername("1");
        getRatingDAO().save(rating3);

        Rating rating4 = new Rating();
        rating4.setRatingScore(2);
        rating4.setComment("Waited 20 minutes for him...");
        rating4.setRatedUsername("1");
        rating4.setRatingUsername("IknowNothing");
        getRatingDAO().save(rating4);

        Rating rating5 = new Rating();
        rating5.setRatingScore(5);
        rating5.setComment("She was really generous and came on time.");
        rating5.setRatedUsername("JaxTellerHarley");
        rating5.setRatingUsername("1");
        getRatingDAO().save(rating5);










    }

    /**
     * Επιστρέφει το DAO των parking request.
     * @return Το DAO των parking request
     */
    public static ParkingRequestDAO getRequestDAO() {
        return PRDao;
    }

    /**
     * Επιστρέφει το DAO των parking spaces.
     * @return Το DAO των parking spaces
     */
    public static ParkingSpaceDAO getParkingDAO() {
        return PSDao;
    }

    /**
     * Επιστρέφει το DAO των user.
     * @return Το DAO των user
     */
    public static UserDAO getUserDAO() {
        return UDao;
    }


    /**
     * Επιστρέφει το DAO των ratings.
     * @return Το DAO των ratings
     */
    public static RatingDAO getRatingDAO() {
        return RDao;
    }

}

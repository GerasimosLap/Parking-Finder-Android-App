package com.example.parking.domain;

import com.example.parking.util.Credits;
import com.example.parking.util.TimeRange;
import com.example.parking.util.ZipCode;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParkingSpaceTest {

    private ParkingSpace parkingSpace;
    private Address address;
    private boolean availability;
    private Credits price;
    private TimeRange timeRange;
    private Date timeOfExchange;
    private User parkedUser;
    private String plate;

    @Before
    public void setup(){
        parkingSpace=new ParkingSpace();
        address= new Address("Wall St.","23", new ZipCode(71310));
        parkingSpace.setAddress(address);
        availability=false;
        parkingSpace.setAvailability(availability);
        price=new Credits(10);
        parkingSpace.setPrice(price);
        timeRange= new TimeRange(LocalDateTime.now(),30);
        parkingSpace.setTimeRange(timeRange);
        timeOfExchange = new Date();
        parkingSpace.setTimeOfExchange(timeOfExchange);
        parkedUser = new User();
        parkingSpace.setParkedUser(parkedUser);
        plate = "OPA1234";
        parkingSpace.setPlate(plate);
    }

    @Test
    public void FullConTest() {
        User user1= new User ("John", "Snow", "6980808080", "johnsnow@gmail.com", "IknowNothing","valarmorgulis",new Credits(10),address, new ArrayList<Rating>(),new ArrayList<Vehicle>());
        ParkingSpace p1 = new ParkingSpace(address,false,new Credits(6),new TimeRange(30),new Date(),user1,"KTO0512");
    }

    @Test
    public void getAddressTest() {
        assertEquals(address,parkingSpace.getAddress());
    }

    @Test
    public void setAddressTest() {
        Address ad = new Address("Derigny","23", new ZipCode(71310));
        parkingSpace.setAddress(ad);
        assertEquals(ad,parkingSpace.getAddress());
    }

    @Test
    public void getAvailabilityTest() {
        assertEquals(availability,parkingSpace.getAvailability());
    }

    @Test
    public void setAvailabilityTest() {
        parkingSpace.setAvailability(true);
        assertTrue(parkingSpace.getAvailability());
    }

    @Test
    public void getPriceTest() {
        assertEquals(price,parkingSpace.getPrice());
    }

    @Test
    public void setPriceTest() {
        Credits cre = new Credits(15);
        parkingSpace.setPrice(cre);
        assertEquals(cre,parkingSpace.getPrice());
    }

    @Test
    public void getTimeRangeTest() {
        assertEquals(timeRange,parkingSpace.getTimeRange());
    }

    @Test
    public void setTimeRangeTest() {
        TimeRange tr = new TimeRange(LocalDateTime.now(),60);
        parkingSpace.setTimeRange(tr);
        assertEquals(tr,parkingSpace.getTimeRange());
    }

    @Test
    public void getTimeOfExchangeTest() {
        assertEquals(timeOfExchange,parkingSpace.getTimeOfExchange());
    }

    @Test
    public void setTimeOfExchangeTest() {
        Date d1 = new Date(2019, 12, 13);
        parkingSpace.setTimeOfExchange(d1);
        assertEquals(d1,parkingSpace.getTimeOfExchange());
    }



    @Test
    public void getPlateTest() {
        assertEquals(plate,parkingSpace.getPlate());
    }

    @Test
    public void setPlateTest() {
        String s1 = "IEK4151";
        parkingSpace.setPlate(s1);
        assertEquals(s1,parkingSpace.getPlate());
    }

    @Test
    public void getParkedUserTest() {
        assertEquals(parkedUser,parkingSpace.getParkedUser());
    }

    @Test
    public void setParkedUserTest() {
        //User pu= new User ("Harry", "Potter", "6991850231", "harrypotter@gmail.com", "TheBoyWhoLived","youareawizardharry",address, new ArrayList<Rating>(),new ArrayList<Vehicle>());
       // parkingSpace.setParkedUser(pu);
        //assertEquals(pu,parkingSpace.getParkedUser());
    }

    @Test
    public void makeParkingUnavailableTest() {
        parkingSpace.makeParkingUnavailable();
        assertFalse(parkingSpace.getAvailability());
        assertEquals(new Date(System.currentTimeMillis()),parkingSpace.getTimeOfExchange());
    }

    @Test
    public void makeParkingAvailableTest() {
        parkingSpace.makeParkingAvailable();
        assertTrue(parkingSpace.getAvailability());
        assertEquals(new Date(),parkingSpace.getTimeOfExchange());
    }

    @Test
    public void toStringTest() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(timeOfExchange);
        String str="ParkingSpace{" +
                "address=" + address +
                ", availability=" + availability +
                ", price=" + price +
                ", timeOfExchange=" + strDate +
                ", parkedUser=" + parkedUser +
                ", plate='" + plate + '\'' +
                '}';
        assertEquals(str,parkingSpace.toString());
    }

}
package com.example.parking.ui.findParkingTest;

import com.example.parking.dao.ParkingSpaceDAO;
import com.example.parking.dao.UserDAO;
import com.example.parking.domain.ParkingRequest;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.memorydao.ParkingSpaceDAOMemory;
import com.example.parking.memorydao.UserDAOMemory;
import com.example.parking.ui.findParking.FindParkingPresenter;
import com.example.parking.util.Credits;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FindParkingPresenterTest {
    private FindParkingViewStub view;
    private ParkingSpaceDAO pDAO;
    private List<ParkingSpace> localParkingSpaces = new ArrayList<>();
    private ParkingSpace p1;
    private FindParkingPresenter presenter;


    @Before
    public void setup(){
        view = new FindParkingViewStub();
        pDAO = new ParkingSpaceDAOMemory();
        p1 = new ParkingSpace();
        p1.setPlate("FIRST PLATE");
        p1.setPrice(new Credits(1));
        pDAO.save(p1);
        localParkingSpaces.add(p1);
        ParkingSpace p2 = new ParkingSpace();
        p2.setPlate("SECONDS PLATE");
        p2.setPrice(new Credits(2));
        pDAO.save(p2);
        localParkingSpaces.add(p2);
        presenter = new FindParkingPresenter(view, pDAO);
    }


    /**
     * Έλεγχος για το αν υπάρχει ένα parking space
     */
    @Test
    public void findParkingSpace(){
        ParkingSpace p = new ParkingSpace();
        p.setPlate("OOO");

        Assert.assertNull(pDAO.find(p));
        Assert.assertEquals(pDAO.find(p1), p1);
    }

    /**
     * Έλεγοχς για το zip code
     */
    @Test
    public void validateZip()
    {
        Assert.assertTrue(presenter.validateZip());
        view.setErrorToZip("error");
        Assert.assertFalse(presenter.validateZip());
        view.setErrorToZip("1111111");
        Assert.assertFalse(presenter.validateZip());
    }

    /**
     * Ελέγχει την σωστή λειτουργία της ανάθεσης και σύγκρισης της χρονικής στιγμής που θα φτάσει
     * ο χρήστης που έχει κάνει το αίτημα.
     */
    @Test
    public void expectedArrivalTimeValidation()
    {
        LocalDateTime d1 = LocalDateTime.now().plusMinutes(10);
        LocalDateTime d2 = LocalDateTime.now().plusMinutes(40);

        Assert.assertTrue(view.getExpectedArrivalDateTime().isAfter(d1));
        Assert.assertTrue(view.getExpectedArrivalDateTime().isBefore(d2));
    }
}

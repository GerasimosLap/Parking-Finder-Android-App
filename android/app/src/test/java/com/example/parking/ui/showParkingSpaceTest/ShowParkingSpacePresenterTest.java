package com.example.parking.ui.showParkingSpaceTest;

import com.example.parking.domain.ParkingSpace;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.showParkingSpace.ShowParkingPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowParkingSpacePresenterTest {
    private ShowParkingSpaceViewStub view;
    private ShowParkingPresenter presenter;
    private ParkingSpace space;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        view = new ShowParkingSpaceViewStub();
        space = MemoryInitializer.getParkingDAO().findAll().get(0);
        presenter = new ShowParkingPresenter(view, MemoryInitializer.getUserDAO(),MemoryInitializer.getParkingDAO(),MemoryInitializer.getRequestDAO(),MemoryInitializer.getRatingDAO(),space);
    }

    /**
     * Έλεγχος για προσθήκη parking space.
     */
    @Test
    public void addTest(){
        int size=MemoryInitializer.getRequestDAO().findAll().size();
        presenter.add(space);
        Assert.assertEquals(size+1,MemoryInitializer.getRequestDAO().findAll().size());
    }

}

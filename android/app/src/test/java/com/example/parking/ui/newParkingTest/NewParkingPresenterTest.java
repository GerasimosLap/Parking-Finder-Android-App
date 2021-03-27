package com.example.parking.ui.newParkingTest;

import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.newParking.NewParkingPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewParkingPresenterTest {
    private NewParkingPresenter presenter;
    private NewParkingViewStub view;
    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        view = new NewParkingViewStub();
        view.setUsername("ok");
        presenter = new NewParkingPresenter(view, MemoryInitializer.getUserDAO(),MemoryInitializer.getParkingDAO());
    }

    /**
     * Επαληθεύει πως η αποθήκευση, η ανάκτηση και η διαγραφή των θέσεων απο το DAO γίνεται σωστά
     */
    @Test
    public void validateParkingSpaceIsSaved()
    {
        view.setCredits("9");
        view.setPlate("YNZ9039");
        view.setStreet("street");
        view.setZip("19239");
        view.setStreetno("99");

        int size = MemoryInitializer.getParkingDAO().findAll().size();
        presenter.add();
        Assert.assertEquals("Parking space added!",view.getToast());
        Assert.assertEquals(size+1,MemoryInitializer.getParkingDAO().findAll().size());
    }
}

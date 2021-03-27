package com.example.parking.ui.viewVehiclesTest;

import com.example.parking.domain.Vehicle;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.viewVehicles.ViewVehiclesPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class viewVehiclesPresenterTest {

    private ViewVehiclesViewStub view;
    private ViewVehiclesPresenter presenter;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        MemoryInitializer.prepareData();
        view = new ViewVehiclesViewStub();
        view.setIntentUsername("ok");
        presenter = new ViewVehiclesPresenter(view,MemoryInitializer.getUserDAO());
    }

    /**
     * Έλεγχος αν εμφανίζονται σωστά τα κουμπία τα vehicles.
     */
    @Test
    public void test(){
        int number_of_clicks = view.getTimesClickedItem(0);
        Vehicle vehicle = MemoryInitializer.getUserDAO().find("ok").getVehicles().get(0);
        presenter.viewOneVehicle(vehicle);
        Assert.assertEquals(number_of_clicks+1,view.getTimesClickedItem(0));
    }
}

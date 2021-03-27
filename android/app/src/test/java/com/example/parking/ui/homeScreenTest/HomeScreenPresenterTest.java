package com.example.parking.ui.homeScreenTest;

import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.homescreen.HomeScreenPresenter;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class HomeScreenPresenterTest {
    private HomeScreenPresenter presenter;
    private HomeScreenPresenterViewStub view;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setUp()
    {
        view = new HomeScreenPresenterViewStub();
        view.setUsername("WhoIsMyFather");
        presenter = new HomeScreenPresenter(view, MemoryInitializer.getUserDAO());
    }

    @Test
    public void testSpaceIntent()
    {
        for(int i = 0; i < 1; i++) {
            presenter.spaceIntent();
        }

        assertEquals(1, view.getSpaceClicks());
    }

    @Test
    public void testFalseSpaceIntent()
    {
        view.setUsername("ffff");
        presenter.spaceIntent();

        assertEquals("Please add a vehicle to continue.", view.getToast());
    }

    @Test
    public void testRequestIntent()
    {
        for(int i = 0; i < 2; i++) {
            presenter.requestIntent();
        }

        assertEquals(2, view.getRequestClicks());
    }

    @Test
    public void testFalseRequestIntent()
    {
        view.setUsername("ffff");
        presenter.requestIntent();

        assertEquals("Please add a vehicle to continue.", view.getToast());
    }
    @Test
    public void testProfileIntent()
    {
        for(int i = 0; i < 23; i++) {
            presenter.profileIntent();
        }

        assertEquals(23, view.getProfileClicks());
    }

    @Test
    public void testNotificationIntent()
    {
        for(int i = 0; i < 6; i++) {
            presenter.notificationIntent();
        }

        assertEquals(6, view.getNotificationClicks());
    }
}

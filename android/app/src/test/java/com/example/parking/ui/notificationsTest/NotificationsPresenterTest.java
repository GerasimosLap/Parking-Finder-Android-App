package com.example.parking.ui.notificationsTest;

import com.example.parking.domain.ParkingRequest;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.notifications.NotificationsPresenter;
import com.example.parking.util.Pin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NotificationsPresenterTest {
    private NotificationsViewStub view;
    private NotificationsPresenter presenter;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        MemoryInitializer.prepareData();
        view = new NotificationsViewStub();
        view.setUsername("ok");
        presenter = new NotificationsPresenter(view,MemoryInitializer.getRequestDAO(),MemoryInitializer.getUserDAO());
    }

    /**
     * Έλεγχος για έγκυρο ή μη έγκυρο pin.
     */
    @Test
    public void validateParking(){
        ParkingRequest request = view.getPinReq().get(0);
        int requestFunds = request.getParkingSpace().getPrice().getPoints();

        int requestingUserPointsBefore = request.getRequestingUser().getCredits().getPoints();

        int parkedUserPointBefore = request.getParkingSpace().getParkedUser().getCredits().getPoints();

        presenter.validateParking(request,new Pin(4444));
        assertEquals("Wrong pin.",view.getToast());

        presenter.validateParking(request,new Pin(5555));
        assertEquals("Transaction complete",view.getToast());

        assertEquals(requestingUserPointsBefore-requestFunds,MemoryInitializer.getUserDAO().find(request.getRequestingUser().getUsername()).getCredits().getPoints());

        assertEquals(parkedUserPointBefore+requestFunds,MemoryInitializer.getUserDAO().find(request.getParkingSpace().getParkedUser().getUsername()).getCredits().getPoints());

        assertNull(MemoryInitializer.getRequestDAO().find(request));
    }

    /**
     * Έλεγχος για αποδοχή parking request.
     */
    @Test
    public void approveRequest(){
        ParkingRequest request = view.getToApprove().get(0);
        int pin = presenter.approveRequest(request);
        assertEquals("Pin generated",view.getToast());
        assertEquals(pin,MemoryInitializer.getRequestDAO().find(request).getPin().getPin());
    }

    /**
     * Έλεγχος για απόρριψη parking request.
     */
    @Test
    public void denyRequest(){
        ParkingRequest request = view.getToApprove().get(0);
        presenter.denyRequest(request);
        assertEquals("Request denied",view.getToast());
        assertNull(MemoryInitializer.getRequestDAO().find(request).getDate());
    }

    /**
     * Έλεγχος για απόρριψη parking request και δημιουργία αρνητικής κριτικής.
     */
    @Test
    public void createRating(){
        ParkingRequest request = view.getToApprove().get(0);
        int size_of_request_dao = MemoryInitializer.getRequestDAO().findAll().size();
        int size_of_rating_dao = MemoryInitializer.getRatingDAO().findAll().size();

        presenter.createRating(request);
        assertEquals(size_of_rating_dao+1,MemoryInitializer.getRatingDAO().findAll().size());
        assertEquals(size_of_request_dao-1,MemoryInitializer.getRequestDAO().findAll().size());
    }
}

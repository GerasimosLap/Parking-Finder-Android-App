package com.example.parking.ui.viewUserTest;

import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.viewUser.UserProfilePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ViewUserPresenterTest {

    private MemoryInitializer dataHelper;
    private UserProfilePresenter presenter;
    private ViewUserViewStub view;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        dataHelper = new MemoryInitializer();
        MemoryInitializer.prepareData();
        view = new ViewUserViewStub();
        view.setIntentUsername("ok");
    }

    /**
     * Test για την επεξεργασία User.
     */
    @Test
    public void editUser() {
        presenter = new UserProfilePresenter(view, MemoryInitializer.getUserDAO());

        view.setFirstName(" ");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"firstname");
        Assert.assertEquals(view.getErrorMessage(),"First name must be more than 3 characters and up to 10.");


        view.setFirstName("Ted");
        view.setLastName(" ");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"lastname");
        Assert.assertEquals(view.getErrorMessage(),"Last name must be more than 3 characters and up to 25.");

        view.setEmail("ted");
        view.setLastName("Mosby");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"email");
        Assert.assertEquals(view.getErrorMessage(),"Email must be more than than 10 characters and up to 150.");

        view.setEmail("tedmosby@gmail.com");
        view.setStreet("a");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"street");
        Assert.assertEquals(view.getErrorMessage(),"Street must be more than 3 characters and up to 15.");

        view.setStreetNo("");
        view.setStreet("W. 85th Street");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"streetno");
        Assert.assertEquals(view.getErrorMessage(),"Street No. must be more than 3 characters and up to 3.");

        view.setStreetNo("150");
        view.setZip("1551");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"zipcode");
        Assert.assertEquals(view.getErrorMessage(),"ZIP Code must be 5 characters.");

        view.setPhone("10");
        view.setZip("15123");
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"phone");
        Assert.assertEquals(view.getErrorMessage(),"Phone must be more 10 characters.");

        view.setPhone("6980808000");
        view.setCredits(-10);
        presenter.update();
        Assert.assertEquals(view.getErrorTitle(),"credits");
        Assert.assertEquals(view.getErrorMessage(),"Credits must not be negative.");

        view.setCredits(40);
        presenter.update();

        Assert.assertEquals(view.getFinishMessage(), "User: "+view.getIntentUsername()+ " updated!");
    }
}

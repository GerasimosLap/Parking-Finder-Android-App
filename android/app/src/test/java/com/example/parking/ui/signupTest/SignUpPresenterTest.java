package com.example.parking.ui.signupTest;

import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.signup.SignUpPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignUpPresenterTest {
    private SignUpViewStub view;
    private SignUpPresenter presenter;

    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        MemoryInitializer.prepareData();
        view=new SignUpViewStub();
        presenter=new SignUpPresenter(view,MemoryInitializer.getUserDAO());
    }

    /**
     * Έλεγχος για sign up.
     */
    @Test
    public void testSignUp(){
        view.setName("");
        presenter.add();
        assertEquals("Name cannot be empty",view.getError());

        view.setName("kon");
        view.setSurname("");
        presenter.add();
        assertEquals("Last name cannot be empty",view.getError());

        view.setSurname("kons");
        view.setPhone("");
        presenter.add();
        assertEquals("Phone cannot be empty",view.getError());


        view.setPhone("698080");
        presenter.add();
        assertEquals("Phone must be 10 digits",view.getError());

        view.setPhone("6969696969");
        view.setEmail("");
        presenter.add();
        assertEquals("Email cannot be empty",view.getError());

        view.setEmail("fff");
        presenter.add();
        assertEquals("Invalid email",view.getError());

        view.setEmail("kkk@kkk.com");
        view.setUsername("");
        presenter.add();
        assertEquals("Username cannot be empty",view.getError());

        view.setUsername("ko");
        presenter.add();
        assertEquals("Username must be more than 3 characters",view.getError());

        view.setUsername("kons");
        view.setPassword("");
        presenter.add();
        assertEquals( "Password cannot be empty",view.getError());

        view.setPassword("mmm");
        presenter.add();
        assertEquals( "Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character",view.getError());

        view.setPassword("okok1111");
        view.setZip("");
        presenter.add();
        assertEquals(  "ZIP Code cannot be empty",view.getError());

        view.setZip("111");
        presenter.add();
        assertEquals(  "ZIP Code must be 5 digits",view.getError());

        view.setZip("55555");
        view.setStreet("");
        presenter.add();
        assertEquals(  "Street cannot be empty",view.getError());

        view.setStreet("street");
        view.setStreetno("");
        presenter.add();
        assertEquals(  "Street Number cannot be empty",view.getError());

        view.setStreetno("55");
        presenter.add();
        assertEquals(  "registered",view.getFinish());
    }
}

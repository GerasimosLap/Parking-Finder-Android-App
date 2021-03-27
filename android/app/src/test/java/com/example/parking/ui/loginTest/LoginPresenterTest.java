package com.example.parking.ui.loginTest;

import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LoginPresenterTest {
    private LoginPresenterViewStub view;
    private LoginPresenter presenter;
    /**
     * Αρχικοποιήσεις.
     */
    @Before
    public void setup(){
        MemoryInitializer.prepareData();
        view = new LoginPresenterViewStub();
        presenter = new LoginPresenter(view,MemoryInitializer.getUserDAO());
    }

    /**
     * Έλεγχος για το αν υπάρχει ο user.
     */
    @Test
    public void loginUserExists(){
        view.setUsername("ok");
        view.setPassword("2");
        presenter.login(view.getUsername(),view.getPassword());
        assertEquals("Logged in",view.getToast());
    }

    /**
     * Έλεγχος όταν δεν υπάρχει ο user.
     */
    @Test
    public void loginUserDoesntExist(){
        view.setUsername("ok");
        view.setPassword("ok");
        presenter.login(view.getUsername(),view.getPassword());
        assertEquals("Wrong username or password",view.getToast());
    }

    /**
     * Έλεγχος για το forgot password όταν δεν υπάρχει ο user.
     */
    @Test
    public void forgotUserDoesntExistTest(){
        view.setUsername("okkk");
        presenter.forgot(view.getUsername());
        assertEquals("Username doesn't exist",view.getToast());
    }
    /**
     * Έλεγχος για το forgot password όταν υπάρχει ο user.
     */
    @Test
    public void forgotUserExistsTest(){
        view.setUsername("ok");
        presenter.forgot(view.getUsername());
        assertEquals("2",view.getToast());
    }

    @Test
    public void testSignUpClicked(){
        for(int i=0;i<10;i++){
            presenter.signup();
        }
        assertEquals(10,view.getTimesClickedSignUp());
    }
}

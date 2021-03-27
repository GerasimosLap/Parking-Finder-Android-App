package com.example.parking.ui.login;

import com.example.parking.dao.UserDAO;
import com.example.parking.domain.User;

public class LoginPresenter {
    private LoginView view;
    private UserDAO dao;

    /**
     * Αρχικοποεί τον Presenter.
     * @param view Ένα instance του view
     * @param dao Ένα instance της κλάσης UserDao
     */

    public LoginPresenter(LoginView view, UserDAO dao){
        this.view=view;
        this.dao=dao;
    }

    /**
     * Αρχικοποεί τον Presenter.
     * @param username Ένα string το οποίο είναι το όνομα του χρήστη που θέλει να κάνει login
     * @param password Ένα string το οποίο είναι ο κωδικός του χρήστη που θέλει να κάνει login
     */
    public void login(String username,String password){
        User user =  dao.login(username,password);
        if(user!=null){
            view.createToast("Logged in");
            view.moveOn();
        }else{
            view.createToast("Wrong username or password");
        }
    }

    /**
     * Μεταφέρει τον χρήστη στο activity SignUp
     * μόλις πατήσει το κουμπί sign up.
     */
    public void signup(){
        view.signup();
    }

    /**
     * Εμφανίζει τον κωδικό στον χρήστη.
     * @param username Ένα string το οποίο είναι το όνομα του χρήστη που θέλει να θυμηθεί τον κωδικό του
     */
    public String forgot(String username){
        User user =  dao.find(username);
        if(user!=null){
            view.createToast(user.getPassword());
            return user.getPassword();
        }else{
            view.createToast("Username doesn't exist");
            return "Username doesn't exist";
        }
    }
}

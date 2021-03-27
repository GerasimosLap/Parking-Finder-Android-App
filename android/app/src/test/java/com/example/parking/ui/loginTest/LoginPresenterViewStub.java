package com.example.parking.ui.loginTest;


import com.example.parking.ui.login.LoginView;

public class LoginPresenterViewStub implements LoginView
{
    String username,password,toast;
    int times_clicked;

    public LoginPresenterViewStub(){
        username = password = toast ="";
        times_clicked=0;
    }

    @Override
    public void moveOn() {

    }

    @Override
    public void createToast(String text) {
        toast = text;
    }

    public String getToast() {
        return toast;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username=username;
    }

    public void setPassword(String pass) {
        this.password=pass;
    }
    @Override
    public void signup() {
        times_clicked++;
    }

    public int getTimesClickedSignUp(){
        return times_clicked;
    }

}

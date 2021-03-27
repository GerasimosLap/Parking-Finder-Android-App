package com.example.parking.ui.signupTest;
import android.os.Bundle;

import com.example.parking.ui.signup.SignUpView;

public class SignUpViewStub implements SignUpView
{
    private String name,surname,phone,email,username,password,street,streetno,zip,error,errorobject,finish;

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getStrN() {
        return streetno;
    }

    @Override
    public String getZipCode() {
        return zip;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void makeToast(String str) {

    }

    @Override
    public void setError(String EditText, String error) {
        this.errorobject=EditText;
        this.error=error;
    }

    public String getFinish(){
        return finish;
    }

    public String getError(){
        return error;
    }

    @Override
    public void successfullyFinishActivity(String message) {
        finish=message;
    }
}

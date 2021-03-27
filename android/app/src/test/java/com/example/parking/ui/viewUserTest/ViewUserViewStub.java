package com.example.parking.ui.viewUserTest;


import com.example.parking.ui.viewUser.UserProfilePresenter;
import com.example.parking.ui.viewUser.UserProfileView;

public class ViewUserViewStub implements UserProfileView
{
    private String firstName,lastName,email,zipCode,street,streetNo,phone,finishMessage,errorTitle,errorMessage,intentUsername;
    private int credits;

    private UserProfilePresenter presenter;

    public void setPresenter(UserProfilePresenter presenter) {
        this.presenter = presenter;
    }


    public ViewUserViewStub()
    {
        firstName = lastName  = email = zipCode=street=streetNo=intentUsername="";
    }
    @Override
    public void setCredits(int credits) {
        this.credits=credits;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String value) {
        this.firstName=value;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String value) {
        this.lastName=value;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String value) {
        email=value;
    }

    @Override
    public String getZip() {
        return zipCode;
    }

    @Override
    public void setZip(String value) {
        zipCode=value;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String value) {
        street=value;
    }

    @Override
    public String getStreetNo() {
        return streetNo;
    }

    @Override
    public void setStreetNo(String value) {
        streetNo=value;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String value) {
        phone=value;
    }

    @Override
    public String getUsername() {
        return intentUsername;
    }

    @Override
    public void successfullyFinishActivity(String message) {
        finishMessage = message;
    }

    @Override
    public String getErrorTitle()
    {
        return errorTitle;
    }

    @Override
    public String getFinishMessage() {
        return finishMessage;
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }


    public void showErrorMessage(String title, String message)
    {
        errorTitle = title;
        errorMessage = message;
    }

    public void setIntentUsername(String username){
        intentUsername=username;
    }

    public String getIntentUsername (){
        return intentUsername;
    }

}

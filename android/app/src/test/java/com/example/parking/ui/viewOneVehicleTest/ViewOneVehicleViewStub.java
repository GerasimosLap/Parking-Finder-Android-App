package com.example.parking.ui.viewOneVehicleTest;
import com.example.parking.ui.viewOneVehicle.ViewOneVehiclePresenter;
import com.example.parking.ui.viewOneVehicle.ViewOneVehicleView;
import com.example.parking.util.Colour;

public class ViewOneVehicleViewStub implements ViewOneVehicleView
{
    private String errorTitle,errorMessage,finishMessage,brand,model,plate,text,username;
    private Colour colour;
    private int length;
    private boolean invisibility = false;

    private ViewOneVehiclePresenter presenter;

    public void setPresenter(ViewOneVehiclePresenter presenter) {
        this.presenter = presenter;
    }


    public ViewOneVehicleViewStub()
    {
        brand = model  = text = "";
        length=0;
    }
    @Override
    public void setBrand(String value) {
        brand=value;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setModel(String value) {
        model=value;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setPlate(String value) {
        plate=value;
    }

    @Override
    public String getPlateText() {
        return plate;
    }

    @Override
    public void setLength(int value) {
        length=value;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setText(String value) {
        text=value;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public void setColour(final String color)
    {

    }

    @Override
    public String getUserName() {
        return username ;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public void setInvisibility()
    {
        this.invisibility=true;
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
        this.username=username;
    }

    public String getIntentUsername (){
        return username;
    }

    public void setIntentPlate(String plate){
        this.plate=plate;
    }

    public String getIntentPlate (){
        return plate;
    }
}

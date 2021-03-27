package com.example.parking.ui.viewVehiclesTest;

import com.example.parking.domain.Vehicle;
import com.example.parking.ui.viewVehicles.ViewVehiclesPresenter;
import com.example.parking.ui.viewVehicles.ViewVehiclesView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewVehiclesViewStub implements ViewVehiclesView
{
    ViewVehiclesPresenter presenter;
    String intentUsername;
    private Map<Integer, Integer> clicksCount;
    ArrayList<Vehicle> DaoVehicles;
    public ViewVehiclesViewStub()
    {
        intentUsername  = "";
        clicksCount = new HashMap<Integer, Integer>();
        DaoVehicles = new ArrayList<>();

    }

    public void viewOneVehicle(Vehicle vehicle){
        int y=0;
        for(int i=0; i<DaoVehicles.size();i++){
            if(DaoVehicles.get(i).getPlate().equals(vehicle.getPlate())) {y=i; break; }
        }
        clicksCount.put(y, (clicksCount.containsKey(y) ? clicksCount.get(y) : 0)+1);
    }

    public void showVehicles(ArrayList<Vehicle> DaoVehicles){
        for(int i=0; i<DaoVehicles.size();i++){
            clicksCount.put(i, (clicksCount.containsKey(i) ? clicksCount.get(i) : 0)+1);
        }
        this.DaoVehicles=DaoVehicles;
    }

    public void setPresenter(ViewVehiclesPresenter presenter) {
        this.presenter = presenter;
    }

    public String getUserName()
    {
       return intentUsername;
    }

    public int getTimesClickedItem(int uid)
    {
        return clicksCount.containsKey(uid) ? clicksCount.get(uid) : 0;
    }
    public void setIntentUsername(String username){
        intentUsername=username;
    }

    public String getIntentUsername (){
        return intentUsername;
    }


}

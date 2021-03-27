package com.example.parking.ui.homeScreenTest;

import com.example.parking.ui.homescreen.HomeScreenView;

public class HomeScreenPresenterViewStub implements HomeScreenView {
    private int
            RequestClicks,
            SpaceClicks,
            ProfileClicks,
            NotificationClicks=0;
    private String username,toast="";

    public HomeScreenPresenterViewStub(){}
    @Override
    public void spaceIntent() {
        SpaceClicks++;
    }

    @Override
    public void requestIntent() {
        RequestClicks++;
    }

    @Override
    public void profileIntent() {
        ProfileClicks++;
    }

    @Override
    public void notificationIntent() {
        NotificationClicks++;
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public void makeToast(String m) {
        toast=m;
    }

    public int getRequestClicks() {
        return RequestClicks;
    }

    public int getSpaceClicks() {
        return SpaceClicks;
    }

    public int getProfileClicks() {
        return ProfileClicks;
    }

    public int getNotificationClicks() {
        return NotificationClicks;
    }

    public void setUsername(String u){ username=u;}
    public String getToast(){ return toast;}
}

package com.example.parking.ui.homescreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.findParking.FindParking;
import com.example.parking.ui.newParking.NewParkingSpace;
import com.example.parking.ui.notifications.Notifications;
import com.example.parking.ui.viewUser.UserProfile;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenView{
    Button parkingSpace;
    Button request;
    Button profile;
    Button notification;
    HomeScreenPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        parkingSpace = findViewById(R.id.submit);
        request = findViewById(R.id.request);
        profile = findViewById(R.id.profile);
        notification = findViewById(R.id.notification);
        presenter = new HomeScreenPresenter(this, MemoryInitializer.getUserDAO());
        parkingSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.spaceIntent();
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestIntent();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.profileIntent();
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.notificationIntent();
            }
        });

    }

    /**
     * ?????????????????? ?????? Toast
     * @param m ???? ?????????????????????? ?????? toast
     */
    @Override
    public void makeToast(String m){
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }


    /**
     * ???????????????????? ???? ?????????? ?????? ????????????.
     * @return ???? ??????????.
     */
    @Override
    public String getUserName()
    {
        return this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null;
    }

    /**
     * O ?????????????? ?????????????????????? ?????? NewParkingSpace activity
     */
    @Override
    public void spaceIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, NewParkingSpace.class);
        myIntent.putExtra("username",getUserName());
        startActivity(myIntent);
    }


    /**
     * O ?????????????? ?????????????????????? ?????? FindParking activity
     */
    @Override
    public void requestIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, FindParking.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }

    /**
     * O ?????????????? ?????????????????????? ?????? UserProfile activity
     */
    @Override
    public void profileIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, UserProfile.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }

    /**
     * O ?????????????? ?????????????????????? ?????? Notifications activity
     */
    @Override
    public void notificationIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, Notifications.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }


    /**
     * ?????????????? ???????????? Toast ?????? ?????? ???????? ???????? ???? ???? sign up
     * @param requestCode ?? ???????????????????? ??????????????
     * @param resultCode ?? ?????????????? ?????? ??????????????????????????
     * @param data ???? intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            recreate();
            Toast.makeText(this,data.getStringExtra("message_to_toast"),Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == 100)
            recreate();
    }
}

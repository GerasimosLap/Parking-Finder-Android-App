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
     * Εμφανίζει ένα Toast
     * @param m Το περιεχόμενο του toast
     */
    @Override
    public void makeToast(String m){
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }


    /**
     * Επιστρέφει το όνομα του χρήστη.
     * @return Το όνομα.
     */
    @Override
    public String getUserName()
    {
        return this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null;
    }

    /**
     * O χρήστης μεταφέρεται στο NewParkingSpace activity
     */
    @Override
    public void spaceIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, NewParkingSpace.class);
        myIntent.putExtra("username",getUserName());
        startActivity(myIntent);
    }


    /**
     * O χρήστης μεταφέρεται στο FindParking activity
     */
    @Override
    public void requestIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, FindParking.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }

    /**
     * O χρήστης μεταφέρεται στο UserProfile activity
     */
    @Override
    public void profileIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, UserProfile.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }

    /**
     * O χρήστης μεταφέρεται στο Notifications activity
     */
    @Override
    public void notificationIntent(){
        Intent myIntent = new Intent(HomeScreenActivity.this, Notifications.class);
        myIntent.putExtra("username", getUserName());
        startActivity(myIntent);
    }


    /**
     * Δείχνει μήνυμα Toast εάν όλα πάνε καλά με το sign up
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
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

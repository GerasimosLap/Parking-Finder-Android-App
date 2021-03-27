package com.example.parking.ui.showParkingSpace;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.domain.Rating;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.findParking.FindParking;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShowParkingSpace extends AppCompatActivity implements ShowParkingView {
    AlertDialog myDialog;
    ShowParkingPresenter presenter;
    ParkingSpace parkingSpace;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_parking);
        Gson gson = new Gson();
        String parkingSpaceAsAString = getIntent().getStringExtra("parkingSpace");

        parkingSpace = gson.fromJson(parkingSpaceAsAString, ParkingSpace.class);
        presenter = new ShowParkingPresenter(this, MemoryInitializer.getUserDAO(), MemoryInitializer.getParkingDAO(),MemoryInitializer.getRequestDAO(),MemoryInitializer.getRatingDAO(),parkingSpace);

        Button btn = findViewById(R.id.sendParkingRequest);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                presenter.add(parkingSpace);
                Intent myIntent = new Intent(ShowParkingSpace.this, FindParking.class);
                setResult(RESULT_OK, myIntent);
                finish();
            }
        });

        Button reviewsButton = findViewById(R.id.ReviewsofParkedUser);
        reviewsButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowParkingSpace.this);
                String u =getParkedUsername();
                builder.setTitle("Reviews for user " + u );
                builder.setCancelable(true);
                List<Rating> l = MemoryInitializer.getRatingDAO().findAllOfUser(u);
                List<String> toS = new ArrayList<>();
                for(Rating r:l){
                    toS.add(r.toString());
                }
                CharSequence [] helpme = new String[l.size()];
                for(int i=0; i<l.size();i++){
                    helpme[i]=l.get(i).toString();
                }
                builder.setView(LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_show_parking,null));
                builder.setItems(helpme, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface myDialog, int item) {
                    }
                });
                myDialog=builder.create();
                builder.setPositiveButton("Close Reviews", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        myDialog.cancel();
                    }
                });
                builder.show();


            }
        });

    }
    public String getRequestingUser() { return this.getIntent().hasExtra("Username") ? this.getIntent().getExtras().getString("Username") : null; }
    public void setParkedUser(String parkedUsername){ ((TextView) findViewById(R.id.ParkedUser)).setText(parkedUsername);}
    public void setVehicle(String plate){((TextView) findViewById(R.id.ParkedVehicle)).setText(plate);}
    public void setAddress(String zip){((TextView) findViewById(R.id.AddressForRequest)).setText(zip); }
    public String getParkedUsername(){return ((TextView) findViewById(R.id.ParkedUser)).getText().toString(); }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(ShowParkingSpace.this, FindParking.class);
        this.finish();
    }

}

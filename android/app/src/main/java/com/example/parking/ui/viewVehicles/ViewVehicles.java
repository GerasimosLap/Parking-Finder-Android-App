package com.example.parking.ui.viewVehicles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.domain.Vehicle;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.viewOneVehicle.ViewOneVehicle;

import java.util.ArrayList;

public class ViewVehicles extends AppCompatActivity implements ViewVehiclesView {
    private ViewVehiclesPresenter presenter;
    private String intentUsername;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicles);
        presenter = new ViewVehiclesPresenter(this, MemoryInitializer.getUserDAO());
        Button add_vehicle = findViewById(R.id.add_vehicle);
        add_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewVehicles.this, ViewOneVehicle.class);
                myIntent.putExtra("username", getUserName());
                startActivityForResult(myIntent,2);
            }
        });

    }


    public String getUserName()
    {
        return this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null;
    }

    public void setIntentUsername(String username){
        intentUsername=username;
    }

    public String getIntentUsername (){
        return intentUsername;
    }

    /**
     * Εμφανίζει τα vehicles ενος user.
     * @param DaoVehicles Τα vehicles του user.
     */
    public void showVehicles(ArrayList<Vehicle> DaoVehicles){
        int colorBackground = Color.parseColor("#337FFF");
        int colorText = Color.parseColor("#ffffff");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_view_vehicles, null);
        ArrayList<Button> buttons = new ArrayList<>();
        LinearLayout sv = v.findViewById(R.id.search_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, 0, 30);

        int padding = 30;
        for (int i = 0; i < DaoVehicles.size(); i++) {

            LinearLayout newLayout = new LinearLayout(this);
            newLayout.setOrientation(LinearLayout.VERTICAL);
            newLayout.setBackgroundColor(colorBackground);
            Vehicle veh = DaoVehicles.get(i);
            final Button btn = new Button(this);
            btn.setBackgroundColor(colorBackground);
            btn.setText(veh.getBrand() + " "+ veh.getModel());
            btn.setTextSize(12);
            btn.setTextColor(colorText);
            btn.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));
            newLayout.addView(btn);
            buttons.add(btn);
            String info = veh.toString();
            TextView data = new TextView(this);
            data.setText(info);
            data.setTextSize(10);
            data.setTextColor(colorText);
            newLayout.addView(data);
            newLayout.setLayoutParams(layoutParams);
            newLayout.setPadding(padding,padding,padding,padding);
            sv.addView(newLayout);
            setVehicleOnClickListener(btn,veh);

        }
        setContentView(v);
    }

    /**
     * Εμφανίζει το vehicle που θα επιλεγεί.
     * @param b Το button.
     * @param v To vehicle.
     */
    public void setVehicleOnClickListener(Button b,Vehicle v) {
        b.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        presenter.viewOneVehicle(v);
                    }
                });

    }

    /**
     * Εμφανίζει τα στοιχεία ενός vehicle.
     * @param vehicle Τα vehicles που επιλέχθηκε.
     */
    public void viewOneVehicle(Vehicle vehicle){
        Intent myIntent = new Intent(ViewVehicles.this, ViewOneVehicle.class);
        myIntent.putExtra("username", getUserName());
        myIntent.putExtra("plate", vehicle.getPlate());
        startActivityForResult(myIntent,1);
    }

    /**
     * Ξαναδημιουργεί το activity.
     * @param requestCode Ο ζητούμενος κωδικός
     * @param resultCode Ο κωδικός του αποτελέσματος
     * @param data Το intent
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == 1 || requestCode == 2)&& resultCode == Activity.RESULT_OK)
        {
            recreate();
            Toast.makeText(this,data.getStringExtra("message_to_toast"),Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == 100)
            recreate();
        }
}

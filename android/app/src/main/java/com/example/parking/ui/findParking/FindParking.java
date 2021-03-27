package com.example.parking.ui.findParking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.domain.ParkingSpace;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.showParkingSpace.ShowParkingSpace;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FindParking extends AppCompatActivity implements FindParkingView{
    FindParkingPresenter presenter;
    LocalDateTime expectedArrivalDateTime;
    EditText expectedArrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_parking);
        presenter = new FindParkingPresenter(this,MemoryInitializer.getParkingDAO());

        expectedArrival = findViewById(R.id.expectedArrivalFindParking);
        ImageButton btn = findViewById(R.id.SearchButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(expectedArrival.getText().toString().trim().length() == 0){
                    expectedArrival.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    return;
                }else{
                    expectedArrival.setBackgroundResource(android.R.drawable.edit_text);
                    expectedArrivalDateTime = LocalDateTime.now().plusMinutes(Integer.parseInt(expectedArrival.getText().toString()));
                }
                presenter.find();
                makeToast(getZip());
            }
        });
    }

    /**
     * @return Η εκτιμώμενη χρονική στιγμή που θα καταυθάσει στο στημείο ο χρήστης
     */
    public LocalDateTime getExpectedArrivalDateTime(){
        return expectedArrivalDateTime;
    }

    /**
     * @return Ο Τ.Κ.
     */
    public String getZip(){
        return ((EditText) findViewById(R.id.ZipForParking)).getText().toString();
    }


    /**
     * @param error Το μήνυμα λάθους που θα εμφανιστεί στο πεδίο του zip
     */
    public void setErrorToZip(String error){
        ((EditText)findViewById(R.id.ZipForParking)).setError(error);
    }

    /**
     * Εμφανίζει όλες τις έγγυρες θέσεις parking
     * @param DaoParkingSpace Λίστα με τις έγγυρες θέσεις parking
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showParkingSpace(ArrayList<ParkingSpace> DaoParkingSpace){
        int colorBackground = Color.parseColor("#337FFF");
        int colorText = Color.parseColor("#ffffff");


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_find_parking, null);
        ArrayList<Button> buttons = new ArrayList<>();
        LinearLayout sv = v.findViewById(R.id.ResultsList);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 30);
        int padding = 30;
        for (int i = 0; i < DaoParkingSpace.size(); i++) {
            LinearLayout newLayout = new LinearLayout(this);
            newLayout.setOrientation(LinearLayout.VERTICAL);
            newLayout.setBackgroundColor(colorBackground);
            ParkingSpace p = DaoParkingSpace.get(i);
            final Button btn = new Button(this);
            btn.setBackgroundColor(colorBackground);
            String details = "ADDRESS: " + p.getAddress().toString() + " PARKED USER: "+ p.getParkedUser().getUsername() +
                    " AVAILABILITY: " + p.getTimeRange()+ " CREDITS: "+ p.getPrice().getPoints();
            btn.setText(details);
            btn.setTextSize(11);
            btn.setTextColor(colorText);
            btn.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 200));
            newLayout.addView(btn);
            buttons.add(btn);
            String info =  p.getParkedUser().getUsername();
            TextView data = new TextView(this);
            data.setText(info);
            data.setTextSize(10);
            data.setTextColor(colorText);
            newLayout.addView(data);
            newLayout.setLayoutParams(layoutParams);
            newLayout.setPadding(padding,padding,padding,padding);
            sv.addView(newLayout);
            setParkingOnClickListener(btn,p);
        }
        setContentView(v);

    }

    /**
     * Φτιάχνει ένα Toast.
     * @param m Το περιεχόμενο του Toast.
     */
    public void makeToast(String m){
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }

    public void setParkingOnClickListener(Button b,ParkingSpace parkspa) {
        b.setOnClickListener(
                    new View.OnClickListener()
                    {

                        @RequiresApi(api = Build.VERSION_CODES.O)

                        public void onClick(View view)
                        {
                            Gson gson = new Gson();
                            String parkingSpaceAsAString = gson.toJson(parkspa);
                            Intent myIntent = new Intent(FindParking.this, ShowParkingSpace.class);
                            myIntent.putExtra("Username", getUserName());
                            myIntent.putExtra("parkingSpace",parkingSpaceAsAString);
                            startActivityForResult(myIntent,1);

                        }
                    });

    }


    /**
     * @return Το όνομα χρήστη
     */
    public String getUserName()
    {
        return this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String flag=getIntent().hasExtra("CodeForRestart") ? getIntent().getExtras().getString("CodeForRestart") : "bad";
        if(flag.equals("OK")){
            recreate();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
        {
            finish();
        }
    }


}

package com.example.parking.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.domain.ParkingRequest;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.util.Pin;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity implements NotificationsView{
    NotificationsPresenter presenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        presenter = new NotificationsPresenter(this, MemoryInitializer.getRequestDAO(),MemoryInitializer.getUserDAO());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotifications(ArrayList<ParkingRequest> all, String username){

        int colorText = Color.parseColor("#ffffff");


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_notifications, null);
        LinearLayout sv = v.findViewById(R.id.notification);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 30);
        String not="";
        int padding = 30;
        for (int i = 0; i < all.size(); i++) {
            int colorBackground = Color.parseColor("#337FFF");
            ParkingRequest request = all.get(i);
            LinearLayout newLayout = new LinearLayout(this);
            newLayout.setOrientation(LinearLayout.VERTICAL);
            newLayout.setBackgroundColor(colorBackground);
            final Button btn = new Button(this);
            btn.setBackgroundColor(colorBackground);

            btn.setTextSize(10);
            btn.setTextColor(Color.parseColor("#000000"));
            btn.setLayoutParams (new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100));

            TextView data = new TextView(this);
            data.setTextSize(10);
            data.setTextColor(colorText);

            if(request.getRequestingUser().getUsername().equals(username)){
                if(request.getPin()!=null) {
                    data.setText(request.getDate().toString() + ", your request is accepted from:" + request.getParkingSpace().getParkedUser().getUsername());
                    colorBackground=Color.parseColor("#22ff00");
                    not = "You have to go to(Pin is "+request.getPin().getPin()+")"; //notification only with pin display
                    complaint(btn,request);
                }else{
                    if(request.getDate()==null){

                        colorBackground=Color.parseColor("#FF0000");
                        data.setText("your request was rejected from:" + request.getParkingSpace().getParkedUser().getUsername());
                        not = "Your request has been rejected"; //notification only
                    }else {


                        colorBackground=Color.parseColor("#337FFF");
                        data.setText("You have asked for a parking space at "+request.getParkingSpace().getAddress().getStreet()+", from: "+request.getDate().getFrom() + " to: "+request.getDate().getTo()+", your request is pending for:" + request.getParkingSpace().getParkedUser().getUsername());
                        not = "Pending request";  //notification only
                    }
                }
            }else if(request.getParkingSpace().getParkedUser().getUsername().equals(username)){
                if(request.getPin()!=null) {

                    data.setText(request.getDate().toString() + ", your request is pending for:" + request.getRequestingUser().getUsername());
                    colorBackground=Color.parseColor("#FF33EF");
                    enterPinListener(btn,request);
                    not = "Accepted.Awaiting for arrival"; //button, when pressed pin must be entered
                }else{
                    if(request.getDate()==null){
                        data.setText("you rejected a request from:" + request.getRequestingUser().getUsername());
                        colorBackground=Color.parseColor("#FF0000");
                        not = "You rejected a request"; //notification only
                    }else {
                        data.setText(request.getDate().toString() + ", your have a request to approve from:" + request.getRequestingUser().getUsername()+" with average rating "+ MemoryInitializer.getRatingDAO().calculateStats(request.getRequestingUser().getUsername()));
                        colorBackground=Color.parseColor("#BCFF33");

                        setApproveOrNotListener(btn,request);
                        not = "Pending.Awaiting for your approval";
                    }

                }
            }
            data.setBackgroundColor(colorBackground);
            btn.setText(not);
            newLayout.addView(btn);
            newLayout.addView(data);
            newLayout.setLayoutParams(layoutParams);
            newLayout.setPadding(padding,padding,padding,padding);
            sv.addView(newLayout);

        }
        setContentView(v);
    }

    /**
     * Εμφανίζει ένα Toast.
     * @param m Το περιεχόμενο που θα εμφανιστεί
     */
    public void makeToast(String m){
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }

    public String getUserName()
    {
        return this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null;
    }


    public void enterPinListener(Button b,ParkingRequest request) {
        b.setOnClickListener(
                    new View.OnClickListener()
                    {
                        public void onClick(View view)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Notifications.this);
                            builder.setTitle("Pin");
                            builder.setMessage("The requesting user has arrived. Enter the pin he has given you.");
                            builder.setPositiveButton("Confirm",
                                    new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {

                                        }
                                    });
                            builder.setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            dialog.dismiss();
                                        }
                                    });
                            final EditText input = new EditText(Notifications.this);
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            builder.setView(input);
                            AlertDialog dialog = builder.create();

                            dialog.show();
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                            {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(View v)
                                {
                                    if(!input.getText().toString().equals("") && presenter.validateParking(request,new Pin(Integer.valueOf(input.getText().toString())))) {
                                        dialog.dismiss();
                                        recreate();
                                    }
                                }
                            });

                        }
                    });

    }

    public void setApproveOrNotListener(Button b,ParkingRequest request) {
        b.setOnClickListener(
                    new View.OnClickListener()
                    {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        public void onClick(View view)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Notifications.this);

                            builder.setTitle("Request for you parking space");
                            builder.setMessage("Your space at "+request.getParkingSpace().getAddress().getStreet()+" wants to be used by "+request.getRequestingUser().getUsername()+" with average rating "+ MemoryInitializer.getRatingDAO().calculateStats(request.getRequestingUser().getUsername()));

                            builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    presenter.approveRequest(request);

                                    dialog.dismiss();
                                    recreate();
                                }
                            });

                            builder.setNegativeButton("DENY", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // date null
                                    presenter.denyRequest(request);
                                    dialog.dismiss();
                                    recreate();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    });

    }

    public void complaint(Button b,ParkingRequest request) {
        b.setOnClickListener(
                new View.OnClickListener()
                {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    public void onClick(View view)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Notifications.this);

                        builder.setTitle("Complaint for your request");
                        builder.setMessage("Your request at "+request.getParkingSpace().getAddress().getStreet()+" is not available because the user "+request.getParkingSpace().getParkedUser().getUsername()+" is not there. Do you wish to file a compaint?");

                        builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                // validate parking
                                presenter.createRating(request);

                                dialog.dismiss();
                                recreate();
                            }
                        });

                        builder.setNegativeButton("DENY", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // date null
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });

    }
}

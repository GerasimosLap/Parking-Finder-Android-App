package com.example.parking.ui.viewUser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.viewVehicles.ViewVehicles;

public class UserProfile extends AppCompatActivity implements UserProfileView
{
    String intentUsername,ErrorTitle,finishMessage,ErrorMessage;
    UserProfilePresenter presenter;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        presenter = new UserProfilePresenter(this, MemoryInitializer.getUserDAO());
        addClickListeners();

    }

    /**
     * Προσθέτει τα δυναμικά κουμπιά για Update Profile, Manage Vehicles και Add Credits.
     */
    public void addClickListeners()
    {
        findViewById((R.id.saveBtnUserProfile)).setOnClickListener((v) -> {
            presenter.update();
            recreate();
        });

        findViewById((R.id.manageCarsBtnUserProfile)).setOnClickListener((v) -> {
            Intent myIntent = new Intent(this, ViewVehicles.class);
            myIntent.putExtra("username", getUsername());
            startActivityForResult(myIntent,1);
        });

        findViewById((R.id.addCreditsBtnUserProfile)).setOnClickListener((v) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            EditText creditsToBeAdded = findViewById(R.id.creditsToBeAddedUserProfile);

            builder.setTitle("Confirm");
            builder.setMessage(creditsToBeAdded.getText()+" credits will be added to your account.Are you sure?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog
                    int totalCredits = getCredits() +
                            Integer.parseInt(creditsToBeAdded.getText().toString());
                    setCredits(totalCredits);
                    presenter.update();

                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        });
    }
    public String getUsername()
    {
        setIntentUsername(this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null);
        return getIntentUsername();
    }

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */


    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(UserProfile.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.OK, null).create().show();

    }

    public void setIntentUsername(String username){
        intentUsername=username;
    }
    @Override
    public void setCredits(int credits){
        ((TextView)findViewById(R.id.creditsNumUserProfile)).setText(String.valueOf(credits));
    }
    @Override
    public int getCredits() {
        String s = ((TextView) findViewById(R.id.creditsNumUserProfile)).getText().toString();
        return Integer.parseInt(s);
    }



    @Override
    public String getFirstName(){
        return ((EditText) findViewById(R.id.firstNameUserProfile)).getText().toString();
    }
    @Override
    public void setFirstName(String value){
        ((EditText)findViewById(R.id.firstNameUserProfile)).setText(value);
    }

    @Override
    public String getLastName(){
        return ((EditText) findViewById(R.id.lastNameUserProfile6)).getText().toString();
    }
    @Override
    public void setLastName(String value){
        ((EditText)findViewById(R.id.lastNameUserProfile6)).setText(value);
    }


    @Override
    public String getEmail(){
        return ((EditText) findViewById(R.id.emailUserProfile)).getText().toString();
    }
    @Override
    public void setEmail(String value){
        ((EditText)findViewById(R.id.emailUserProfile)).setText(value);
    }

    @Override
    public String getZip(){
        return ((EditText) findViewById(R.id.zipcodeUserProfile)).getText().toString();
    }
    @Override
    public void setZip(String value){
        ((EditText)findViewById(R.id.zipcodeUserProfile)).setText(value);
    }

    @Override
    public String getStreet(){
        return ((EditText) findViewById(R.id.addressUserProfile)).getText().toString();
    }
    @Override
    public void setStreet(String value){
        ((EditText)findViewById(R.id.addressUserProfile)).setText(value);
    }



    @Override
    public String getStreetNo(){
        return ((EditText) findViewById(R.id.streetNo)).getText().toString();
    }
    @Override
    public void setStreetNo(String value){
        ((EditText)findViewById(R.id.streetNo)).setText(value);
    }


    @Override
    public String getPhone(){
        return ((EditText) findViewById(R.id.phoneUserProfile)).getText().toString();
    }
    @Override
    public void setPhone(String value){
        ((EditText)findViewById(R.id.phoneUserProfile)).setText(value);
    }

    public String getIntentUsername (){
        return intentUsername;
    }

    public String getUserName()
    {
        setIntentUsername(this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null);
        return getIntentUsername();
    }

    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */

    @Override
    public void successfullyFinishActivity(String message)
    {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK, retData);
        finish();

    }

    @Override
    public String getErrorTitle() {
        return ErrorTitle;
    }

    @Override
    public String getFinishMessage()
    {
        return finishMessage;
    }

    @Override
    public String getErrorMessage() {
        return ErrorMessage;
    }


}

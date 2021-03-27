package com.example.parking.ui.login;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.ui.homescreen.HomeScreenActivity;
import com.example.parking.ui.signup.SignUp;

public class MainActivity extends AppCompatActivity implements LoginView{
    LoginPresenter presenter;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MemoryInitializer.prepareData();
        Button login = findViewById(R.id.login);
        Button signup = findViewById(R.id.login2);
        Button forgot = findViewById(R.id.forgotpassword);


        presenter = new LoginPresenter(this,MemoryInitializer.getUserDAO());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login(getUsername(),getPassword());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signup();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(true)
                        .setTitle("Password for user " + getUsername()+ " is:")
                        .setMessage(presenter.forgot(getUsername()))
                        .setPositiveButton(R.string.OK, null).create().show();
            }
        });
    }

    /**
     * Δημιουργεί ένα νεο Intent και μεταφέρει τον χρήστη στο activity SignUp
     * μόλις πατήσει το κουμπί sign up.
     */
    public void signup(){
        Intent myIntent = new Intent(this, SignUp.class);
        startActivityForResult(myIntent,1);
    }


    @Override
    public String getUsername(){
        return ((EditText) findViewById(R.id.username)).getText().toString();
    }

    @Override
    public String getPassword(){
        return ((EditText) findViewById(R.id.passwordLogIn)).getText().toString();

    }

    /**
     * Δημιουργεί το νέο Intent που πάει τον logged in
     * χρήστη στην αρχική του οθόνη.
     */
    @Override
    public void moveOn() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("username", getUsername());
        startActivity(intent);
    }


    /**
     * Εμφανίζει ένα Toast.
     * @param text Το περιεχόμενο που θα εμφανιστεί
     */

    @Override
    public void createToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
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

            Toast.makeText(this,data.getStringExtra("message_to_toast"),Toast.LENGTH_SHORT).show();
        }
    }
}

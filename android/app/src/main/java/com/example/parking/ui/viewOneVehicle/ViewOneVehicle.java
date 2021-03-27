package com.example.parking.ui.viewOneVehicle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parking.R;
import com.example.parking.memorydao.MemoryInitializer;
import com.example.parking.util.Colour;

import java.util.Arrays;

public class ViewOneVehicle extends AppCompatActivity implements ViewOneVehicleView{
    private String finishMessage,ErrorMessage,ErrorTitle;
    private String intentUsername,intentPlate;
    private Colour color;
    Spinner spinner;
    Button addVehicleBtn,deleteBtn;
    ViewOneVehiclePresenter presenter;

    /**
     * Δημιουργεί το layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        deleteBtn = findViewById(R.id.deleteVehicleBtn);

        spinner = findViewById(R.id.Color);
        addVehicleBtn = findViewById(R.id.addVehicleBtn);
        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.decide();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.delete();
            }
        });
        String[] names = getNames(Colour.class);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,names);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        color=Colour.Black;
                        break;
                    case 1:
                        color=Colour.White;
                        break;
                    case 2:
                        color=Colour.Gray;
                        break;
                    case 3:
                        color=Colour.Red;
                        break;
                    case 4:
                        color=Colour.Green;
                        break;
                    case 5:
                        color=Colour.Blue;
                        break;
                    case 6:
                        color=Colour.Yellow;
                        break;
                    case 7:
                        color=Colour.Golden;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        presenter = new ViewOneVehiclePresenter(this, MemoryInitializer.getUserDAO());
    }



    public void setBrand(String value)
    {
        ((EditText)findViewById(R.id.brand)).setText(value);
    }
    public String getBrand()
    {
        return ((EditText) findViewById(R.id.brand)).getText().toString();
    }


    public void setModel(String value)
    {
        ((EditText)findViewById(R.id.model)).setText(value);
    }
    public String getModel()
    {
        return ((EditText) findViewById(R.id.model)).getText().toString();
    }



    public void setPlate(String value)
    {
        ((EditText)findViewById(R.id.plate)).setText(value);
    }
    public String getPlateText()
    {
        return ((EditText) findViewById(R.id.plate)).getText().toString();
    }

    @Override
    public void setLength(int value) {
        ((EditText)findViewById(R.id.length)).setText(String.valueOf(value));
    }


    public void setLengthText(int value)
    {
        ((EditText)findViewById(R.id.length)).setText(String.valueOf(value));
    }
    public int getLength()
    {
        String s = ((EditText) findViewById(R.id.length)).getText().toString();
        return Integer.parseInt(s);
    }


    public void setText(String value)
    {
        ((EditText)findViewById(R.id.text)).setText(value);
    }
    public String getText()
    {
        return ((EditText) findViewById(R.id.text)).getText().toString();
    }


    public String getUserName()
    {
        setIntentUsername(this.getIntent().hasExtra("username") ? this.getIntent().getExtras().getString("username") : null);
        return getIntentUsername();
    }

    public String getPlate()
    {
        setIntentPlate(this.getIntent().hasExtra("plate") ? this.getIntent().getExtras().getString("plate") : null);
        return getIntentPlate();
    }

    public void setIntentUsername(String username){
        intentUsername=username;
    }

    public String getIntentUsername (){
        return intentUsername;
    }

    public void setIntentPlate(String plate){
        intentPlate=plate;
    }

    public String getIntentPlate (){
        return intentPlate;
    }

    @Override
    public void setInvisibility(){
        deleteBtn.setVisibility(View.GONE);
    }

    /**
     * Εμφανίζει ένα μήνυμα τύπου alert με
     * τίτλο title και μήνυμα message.
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(ViewOneVehicle.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.OK, null).create().show();
    }

    public String getFinishMessage()
    {
        return finishMessage;
    }

    @Override
    public String getErrorMessage() {
        return ErrorMessage;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSpinner(){

    }

    @Override
    public Colour getColour() {
        return color;
    }

    @Override
    public void setColour(String color){
        spinner.setSelection(getIndex(spinner, color));
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
    /**
     * Το μήνυμα που εμφανίζεται όταν τελειώνει
     * επιτυχώς ένα activity.
     * @param message Το μήνυμα που θα εμφανίσει
     */
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
}


package com.example.parking.domain;

import com.example.parking.util.Colour;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VehicleTest {
    private Vehicle vehicle;
    private Colour colour;
    private int length;//length in cm
    private String text;
    private String plate;
    private String model;
    private String brand;

    @Before
    public void setup(){
        vehicle = new Vehicle();
        colour= Colour.Blue;
        vehicle.setColour(colour);
        length=450;
        vehicle.setLength(length);
        text="Medium SUV";
        vehicle.setText(text);
        plate="IEH1234";
        vehicle.setPlate(plate);
        model="Note";
        vehicle.setModel(model);
        brand="Nissan";
        vehicle.setBrand(brand);
    }



    @Test
    public void ValidPlate() {
        String plateTest = "IEH1510";
        vehicle.setPlate(plateTest);
        assertEquals(plateTest,vehicle.getPlate());
    }

    @Test
    public void InvalidPlate1() {//more/less Chars in plate(NOT 7)
        String plateTest = "IEHK";
        vehicle.setPlate(plateTest);
        assertEquals(plate,vehicle.getPlate());
    }

    @Test
    public void InvalidPlate2() {
        String plateTest = "IE11111";//wrong number of letters(NOT 3)
        vehicle.setPlate(plateTest);
        assertEquals(plate,vehicle.getPlate());
    }

    @Test
    public void InvalidPlate3() {
        String plateTest = "IEH123M";//wrong number of digits(NOT 4)
        vehicle.setPlate(plateTest);
        assertEquals(plate,vehicle.getPlate());
    }




    //Getters Tests
//    @Test
//    public void getColour() {
//        assertEquals(colour,vehicle.getColour());
//    }

    @Test
    public void getLength() {
        assertEquals(length,vehicle.getLength());
    }

    @Test
    public void getText() {
        assertEquals(text,vehicle.getText());
    }

    @Test
    public void getPlate() {
        assertEquals(plate,vehicle.getPlate());
    }

    @Test
    public void getModel() {
        assertEquals(model,vehicle.getModel());
    }

    @Test
    public void getBrand() {
        assertEquals(brand,vehicle.getBrand());
    }

    @Test
    public void toStringTest() {
        String str="Vehicle{" +
                "colour=" + colour +
                ", length=" + length +
                ", text='" + text + '\'' +
                ", plate='" + plate + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                '}';
        assertEquals(str,vehicle.toString());
    }

    @Test
    public void FullConTest() {
        Vehicle veh = new Vehicle(Colour.Golden,511,"Large SUV", "MEA6157","Escalade","Cadillac");
        assertNotNull(veh);
    }

}
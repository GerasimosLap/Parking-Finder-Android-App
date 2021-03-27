package com.example.parking.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PinTest {
    Pin pin;
    @Before
    public void setup(){
        pin = new Pin();
        pin.setPin(1234);
    }

    @Test
    public void FullConTest() {
        Pin p = new Pin(100);
        assertNotNull(p);
    }

    @Test
    public void setCorrectPinTest() {
        assertEquals(1234,pin.getPin());
    }

    @Test
    public void setIncorrectPinTest() {
        pin.setPin(12345);
        assertEquals(1234,pin.getPin());
    }

}

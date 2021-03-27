package com.example.parking.util;

import java.util.Random;

public class Pin {
    private int pin;

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin() {
        this.pin = 0;
    }

    public int getPin() {
        return pin;
    }
    /**
     * Έλεγχος αν το pin είναι 4ψηφιο.
     * @param pin Pin που θέλουμε να γίνει set
     */
    public void setPin(int pin) {
        String stringPin = String.valueOf(pin);
        if(stringPin.length()==4) {
            this.pin = pin;
        }
    }

    public static int createPin(){
        Random rn = new Random();
        return rn.nextInt(9000) + 1000;
    }
}

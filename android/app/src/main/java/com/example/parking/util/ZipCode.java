package com.example.parking.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class ZipCode {
    private int zip;


    public ZipCode(int zip) {
        this.zip = zip;
    }

    public ZipCode() {
        this.zip = 0;
    }

    public int getZip() {
        return zip;
    }

    /**
     * Έλεγχος αν το zip είναι 5ψηφιο.
     * @param zip Zip που θέλουμε να γίνει set
     */
    public void setZip(int zip) {
        String stringZip = String.valueOf(zip);
        if(stringZip.length()==5) {
            this.zip = zip;
        }
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "zip=" + zip +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode zipCode = (ZipCode) o;
        return Objects.equals(zip, zipCode.zip);
    }


}

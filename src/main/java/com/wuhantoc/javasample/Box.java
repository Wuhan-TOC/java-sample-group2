package com.wuhantoc.javasample;

import java.text.DateFormat;

public class Box {

    int location;

    boolean available = true;

    String scannerCode;

    public String getScannerCode() {
        return scannerCode;
    }

    public void setScannerCode() {
        this.scannerCode = String.valueOf(DateFormat.getDateInstance()).concat(String.format("%04d", location));
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

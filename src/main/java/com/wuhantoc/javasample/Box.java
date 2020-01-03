package com.wuhantoc.javasample;

public class Box {

    private int location;

    private boolean available = true;

    private String scannerCode;

    public String getScannerCode() {
        return scannerCode;
    }

    public Box(int location) {
        this.location = location;
    }

    public void setScannerCode() {
        this.scannerCode = String.valueOf(System.currentTimeMillis()).concat(String.format("%04d", this.location));
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

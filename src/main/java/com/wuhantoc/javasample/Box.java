package com.wuhantoc.javasample;

public class Box {

    private int location;

    private boolean available = true;

    public Box(int location) {
        this.location = location;
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

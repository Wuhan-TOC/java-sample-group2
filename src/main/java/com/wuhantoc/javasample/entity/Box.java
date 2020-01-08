package com.wuhantoc.javasample.entity;

public class Box {

    private int location;

    private int lockNumber;

    private boolean available = true;

    public Box(int lockNumber, int location) {
        this.lockNumber = lockNumber;
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

    public int getLockNumber() {
        return lockNumber;
    }

    public void setLockNumber(int lockNumber) {
        this.lockNumber = lockNumber;
    }
}

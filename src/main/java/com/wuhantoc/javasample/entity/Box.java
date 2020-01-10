package com.wuhantoc.javasample.entity;

public class Box {

    private final int location;
    private final int lockerNum;
    private boolean available = true;

    public Box(int lockerNum, int location) {
        this.lockerNum = lockerNum;
        this.location = location;
    }

    public int getLocation() {
        return location;
    }

    public int getLockerNum() {
        return lockerNum;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

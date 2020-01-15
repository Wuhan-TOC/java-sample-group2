package com.wuhantoc.javasample.Locker;

public class Box {

    private final int boxNumber;
    private final int lockerNumber;
    private boolean available = true;

    public Box(int lockerNumber, int boxNumber) {
        this.lockerNumber = lockerNumber;
        this.boxNumber = boxNumber;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

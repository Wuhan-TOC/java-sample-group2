package com.wuhantoc.javasample.locker;

public class Box {

    private final int boxNumber;
    private final int lockerNumber;
    private Bag bag = null;

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


    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}

package com.wuhantoc.javasample.Locker;

public class Ticket {

    private final int lockerNumber;
    private final int boxNumber;
    private final String code;

    public Ticket(int boxNumber, int lockerNumber, String code) {
        this.boxNumber = boxNumber;
        this.lockerNumber = lockerNumber;
        this.code = code;
    }

    public Integer getLockerNumber() {
        return lockerNumber;
    }

    public int getBoxLocation() {
        return boxNumber;
    }

    public String getCode() {
        return code;
    }
}

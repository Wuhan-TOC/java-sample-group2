package com.wuhantoc.javasample.entity;

public class Ticket {

    private Integer lockerNumber;
    private int boxLocation;
    private String code;

    public Ticket(int boxLocation, Integer lockerNumber, String code) {
        this.boxLocation = boxLocation;
        this.lockerNumber = lockerNumber;
        this.code = code;
    }

    public Integer getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(Integer lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public int getBoxLocation() {
        return boxLocation;
    }

    public void setBoxLocation(int boxLocation) {
        this.boxLocation = boxLocation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

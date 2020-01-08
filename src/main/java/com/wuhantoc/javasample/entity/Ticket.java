package com.wuhantoc.javasample.entity;

public class Ticket {

    private Integer lockerNumber;
    private int boxLocation;
    private String scannerCode;

    public Ticket(int boxLocation, Integer lockerNumber, String scannerCode) {
        this.boxLocation = boxLocation;
        this.lockerNumber = lockerNumber;
        this.scannerCode = scannerCode;
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

    public String getScannerCode() {
        return scannerCode;
    }

    public void setScannerCode(String scannerCode) {
        this.scannerCode = scannerCode;
    }
}

package com.wuhantoc.javasample;

import java.util.Date;

public class Ticket {

    private Date createTime;
    private Integer lockerNumber;
    private String scannerCode;

    public Ticket(Date createTime, Integer lockerNumber, String scannerCode) {
        this.createTime = createTime;
        this.lockerNumber = lockerNumber;
        this.scannerCode = scannerCode;
    }

    public String getScannerCode() {
        return scannerCode;
    }
}

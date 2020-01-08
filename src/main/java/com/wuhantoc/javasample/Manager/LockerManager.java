package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockerManager {

    private final int capacity = 24;
    private int lockerNumber = 1;
    private CodeManager codeManager = new CodeManager();

    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new)
            .collect(Collectors.toList());

    public LockerManager(int lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Ticket saveBox() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            availableBox.setAvailable(false);
            String scannerCode = codeManager.generateScannerCode(availableBox);
            return new Ticket(new Date(), lockerNumber, scannerCode);
        }
        return null;
    }

    private Box getAvailableBox() {
        return boxList.stream().filter(Box::isAvailable).findAny().orElse(null);
    }

    public Box unLockBox(String scannerCode) {
        Box box = codeManager.verifyScannerCode(scannerCode);
        if (box != null) {
            box.setAvailable(true);
        }
        return box;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }
}

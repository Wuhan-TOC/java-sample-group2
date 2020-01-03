package com.wuhantoc.javasample;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;
    private ScannerCodeManager scannerCodeManager = new ScannerCodeManager();

    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new)
            .collect(Collectors.toList());

    boolean isAvailable() {
        return getAvailableBox() != null;
    }

    public int getAvailableBoxCount() {
        return (int) boxList.stream().filter(Box::isAvailable).count();
    }

    private Box getAvailableBox() {
        return boxList.stream().filter(Box::isAvailable).findAny().orElse(null);
    }

    public String getScannerCode() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            return scannerCodeManager.generateScannerCode(availableBox);
        }
        return null;
    }

    public Box unLockBox(String scannerCode) {
        return scannerCodeManager.verifyScannerCode(scannerCode);
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }
}

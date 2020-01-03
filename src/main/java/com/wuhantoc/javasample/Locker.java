package com.wuhantoc.javasample;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;
    private ScannerCodeManager scannerCodeManager = new ScannerCodeManager();

    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new)
        .collect(Collectors.toList());

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    boolean isAvailable() {
        return getAvailableBox() != null;
    }

    public Box getAvailableBox() {
        return boxList.stream().filter(Box::isAvailable).findAny().orElse(null);
    }

    String getScannerCode() {
        Box availableBox = getAvailableBox();
        return scannerCodeManager.generateScannerCode(availableBox);
    }

    String lockBox() {
        if (isAvailable()) {
            return getScannerCode();
        }
        return null;
    }

    Box unLockBox(String scannerCode) {
        return scannerCodeManager.verifyScannerCode(scannerCode);
    }
}

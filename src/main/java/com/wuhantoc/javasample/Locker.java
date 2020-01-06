package com.wuhantoc.javasample;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;
    private CodeManager codeManager = new CodeManager();

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

    public String saveBox() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            availableBox.setAvailable(false);
            return codeManager.generateScannerCode(availableBox);
        }
        return null;
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
}

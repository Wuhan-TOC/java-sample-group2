package com.wuhantoc.javasample;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;

    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new).collect(Collectors.toList());

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    boolean isAvailable() {
        return boxList.stream().anyMatch(Box::isAvailable);
    }

    String getScannerCode() {
        Optional<Box> oneBox = boxList.stream().filter(Box::isAvailable).findFirst();

        oneBox.get().setScannerCode();
        oneBox.get().setAvailable(false);

        return oneBox.get().getScannerCode();
    }

    String lockBox () {
        if (isAvailable()) {
            return getScannerCode();
        }
        return null;
    }

    Box unLockBox(String scannerCode) {
        List<Box> boxes = this.boxList.stream().filter(box -> box.getScannerCode() != null && box.getScannerCode().equals(scannerCode)).collect(Collectors.toList());

        if(!boxes.isEmpty()) {
            Box box = boxes.get(0);
            box.setAvailable(true);
            boxList.set(box.getLocation(), box);
            return box;
        }
        return null;
    }
}

package com.wuhantoc.javasample;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;
    private final String SAVE_ACTION = "save";

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new).collect(Collectors.toList());


    public boolean isAvailable() {
        return boxList.stream().anyMatch(Box::isAvailable);
    }

    String getScannerCode() {
        Optional<Box> oneBox = boxList.stream().filter(Box::isAvailable).findFirst();

        oneBox.get().setScannerCode();

        return oneBox.get().getScannerCode();
    }

    String useLocker (String action) {
        if (action.equals(SAVE_ACTION) && isAvailable()) {
            return getScannerCode();
        }
        return null;
    }
}

package com.wuhantoc.javasample;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private final int capacity = 24;
    private List<Box> boxList = IntStream.range(0, capacity).mapToObj(Box::new).collect(Collectors.toList());


    public boolean isAvailable() {
        return boxList.stream().anyMatch(Box::isAvailable);
    }

    String getScannerCode() {
        Optional<Box> oneBox = boxList.stream().filter(Box::isAvailable).findFirst();

        oneBox.get().setScannerCode();

        return oneBox.get().getScannerCode();
    }
}

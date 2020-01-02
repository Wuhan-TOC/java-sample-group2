package com.wuhantoc.javasample;


import java.util.ArrayList;
import java.util.List;

public class Locker {

    private final int capacity = 24;
    private List<Box> boxList = new ArrayList<>(capacity);

    public static Locker initAvailableLocker() {
        Locker locker = new Locker();
        for (int i = 0; i < locker.capacity; i++) {
            Box box = new Box();
            box.setLocation(i);
            locker.boxList.add(box);
        }
        return locker;
    }

    public boolean isAvailable() {
        return boxList.stream().anyMatch(box -> box.isAvailable());
    }
}

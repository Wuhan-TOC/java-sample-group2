package com.wuhantoc.javasample.Manager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockerCollectorManager {

    private final int lockerNum = 10;
    private List<LockerManager> lockerManagerList = IntStream.range(0, lockerNum).mapToObj(lockerNumber -> new LockerManager(lockerNumber+1))
                        .collect(Collectors.toList());

    public LockerManager findLockerByNumber(Integer lockerNumber) {
        return lockerManagerList.stream().filter(lockerManager -> compareLockerNumber(lockerManager, lockerNumber)).findAny().orElse(null);
    }

    private boolean compareLockerNumber(LockerManager lockerManager, Integer lockerNumber) {
        return lockerNumber.equals(lockerManager.getLockerNumber());
    }
}

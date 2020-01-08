package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RobotManager {

    private final int lockerSum = 10;
    private List<LockerManager> lockerManagerList = IntStream.range(0, lockerSum).mapToObj(lockerNumber -> new LockerManager(lockerNumber+1))
                        .collect(Collectors.toList());

    public LockerManager findLockerByNumber(Integer lockerNumber) {
        if (validateLockerNumber(lockerNumber)) {
            return lockerManagerList.stream().filter(lockerManager -> compareLockerNumber(lockerManager, lockerNumber)).findAny().orElse(null);
        }
        return null;
    }

    public LockerManager findFirstNotEmptyLockerManger() {
        return lockerManagerList.stream().filter(lockerManager -> lockerManager.isAvailable()).findFirst().orElse(null);
    }


    public Ticket saveBox() {
        LockerManager lockerManager = findFirstNotEmptyLockerManger();
        if (lockerManager != null) {
            return lockerManager.saveBox();
        }
        return null;
    }

    public Box unLock(Ticket ticket) {
        LockerManager lockerManager = findLockerByNumber(ticket.getLockerNumber());
        return lockerManager.unLockBox(ticket);
    }

    private boolean validateLockerNumber(Integer lockerNumber) {
        return (lockerNumber <= lockerSum) && lockerNumber > 0;
    }

    private boolean compareLockerNumber(LockerManager lockerManager, Integer lockerNumber) {
        return lockerNumber.equals(lockerManager.getLockerNumber());
    }

    public List<LockerManager> getLockerManagerList() {
        return lockerManagerList;
    }

    public int getLockerSum() {
        return lockerSum;
    }

}

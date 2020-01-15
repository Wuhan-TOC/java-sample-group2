package com.wuhantoc.javasample.Robot;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.wuhantoc.javasample.Locker.Box;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;
import org.springframework.util.CollectionUtils;

public class SuperRobot implements Robot {

    private final List<Locker> lockers;

    SuperRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket savePackage() {
        Locker maxLocker = getMaxVacancyRateLockerManager();
        if (maxLocker == null) {
            return null;
        }
        return maxLocker.savePackage();
    }

    public Box getPackage(Ticket ticket) {
        Locker locker = lockers.stream()
            .filter(lockerObj -> lockerObj.getLockerNumber() == ticket.getLockerNumber())
            .findFirst().orElse(null);
        if (locker == null) {
            return null;
        }
        return locker.getPackage(ticket);
    }


    private Locker getMaxVacancyRateLockerManager() {
        if (CollectionUtils.isEmpty(lockers)) {
            return null;
        }
        Locker maxLocker = lockers.get(0);
        for (int i = 1; i < lockers.size(); i++) {
            if (lockers.get(i).getVacancyRate() > maxLocker.getVacancyRate()) {
                maxLocker = lockers.get(i);
            }
        }
        return maxLocker;
    }

    public List<Locker> getLockers() {
        return lockers;
    }
}

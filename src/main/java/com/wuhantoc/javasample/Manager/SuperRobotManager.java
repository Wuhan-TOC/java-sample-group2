package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.util.CollectionUtils;

public class SuperRobotManager {

    private final List<LockerManager> lockerManagers;
    private static final String INIT_ERROR = "lockerSum must same as capacities' size";

    SuperRobotManager(int lockerSum, List<Integer> capacities) {
        if (capacities.size() != lockerSum) {
            throw new RuntimeException(INIT_ERROR);
        }
        lockerManagers = IntStream.range(0, lockerSum).mapToObj(
            lockerNumber -> new LockerManager(lockerNumber + 1, capacities.get(lockerNumber)))
            .collect(Collectors.toList());
    }

    public Ticket savePackage() {
        LockerManager maxLockerManager = getMaxVacancyRateLockerManager();
        if (maxLockerManager == null) {
            return null;
        }
        return maxLockerManager.savePackage();
    }

    public Box getPackage(Ticket ticket) {
        LockerManager lockerManager = lockerManagers.stream()
            .filter(locker -> locker.getLockerNumber() == ticket.getLockerNumber())
            .findFirst().orElse(null);
        if (lockerManager == null) {
            return null;
        }
        return lockerManager.getPackage(ticket);
    }


    private LockerManager getMaxVacancyRateLockerManager() {
        if (CollectionUtils.isEmpty(lockerManagers)) {
            return null;
        }
        LockerManager maxLockerManager = lockerManagers.get(0);
        for (int i = 1; i < lockerManagers.size(); i++) {
            if (lockerManagers.get(i).getVacancyRate() > maxLockerManager.getVacancyRate()) {
                maxLockerManager = lockerManagers.get(i);
            }
        }
        return maxLockerManager;
    }

    public List<LockerManager> getLockerManagers() {
        return lockerManagers;
    }
}

package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Locker;
import java.util.List;
import org.springframework.util.CollectionUtils;

public class SuperRobot extends AbstractRobot {


    SuperRobot(List<Locker> lockers) {
        super(lockers);
    }
    @Override
    public Locker findLockerToSave() {
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
}

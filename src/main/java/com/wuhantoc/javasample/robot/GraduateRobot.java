package com.wuhantoc.javasample.robot;

import com.wuhantoc.javasample.locker.Locker;
import java.util.List;

public class GraduateRobot extends AbstractRobot {

    GraduateRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    protected Locker findLockerToSave() {
        return lockers.stream().filter(locker -> locker.getVacancyRate() != 0.0d).findAny().orElse(null);
    }

}
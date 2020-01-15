package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Box;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;

import java.util.ArrayList;
import java.util.List;

public class GraduateRobot implements Robot {

    private List<Locker> lockers = new ArrayList<>();

    GraduateRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }


    public Locker findLockerByNumber(int lockerNumber) {
        if (validateLockerNumber(lockerNumber)) {
            return  lockers.stream().filter(locker -> locker.getLockerNumber() == lockerNumber).findFirst().orElse(null);
        }
        return null;
    }

    public Locker findFirstNotEmptyLockerManger() {
        return lockers.stream().filter(locker -> locker.getVacancyRate() != 0.0d).findFirst().orElse(null);
    }

    public Ticket savePackage() {
        Locker locker = findFirstNotEmptyLockerManger();
        if (locker != null) {
            return locker.savePackage();
        }
        return null;
    }

    public Box getPackage(Ticket ticket) {
        Locker locker = findLockerByNumber(ticket.getLockerNumber());
        return locker.getPackage(ticket);
    }

    private boolean validateLockerNumber(Integer lockerNumber) {
        return lockerNumber > 0;
    }
}

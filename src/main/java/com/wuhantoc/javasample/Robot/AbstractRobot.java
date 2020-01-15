package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Bag;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;
import java.util.List;

public abstract class AbstractRobot implements Robot {

    List<Locker> lockers;

    AbstractRobot(List<Locker> lockers){
        this.lockers = lockers;
    }

    @Override
    public Ticket savePackage(Bag bag) {
        Locker locker = findLockerToSave();
        if (locker != null) {
            return locker.savePackage(bag);
        }
        return null;
    }

    @Override
    public Bag getPackage(Ticket ticket) {
        Locker locker = findLockerByNumber(ticket.getLockerNumber());
        if(locker != null){
            return locker.getPackage(ticket);
        }
        return null;
    }

    private Locker findLockerByNumber(int lockerNumber) {
        if (validateLockerNumber(lockerNumber)) {
            return  lockers.stream().filter(locker -> locker.getLockerNumber() == lockerNumber).findFirst().orElse(null);
        }
        return null;
    }

    private boolean validateLockerNumber(Integer lockerNumber) {
        return lockerNumber > 0;
    }

    protected abstract Locker findLockerToSave();

    List<Locker> getLockers() {
        return this.lockers;
    }
}

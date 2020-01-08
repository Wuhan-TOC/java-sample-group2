package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockerManager {

    private final int capacity = 24;
    private int availableCount = capacity;
    private int lockerNumber = 1;
    private TicketManager ticketManager = new TicketManager();

    private List<Box> boxList;

    public LockerManager(int lockerNumber) {
        this.lockerNumber = lockerNumber;
        boxList = IntStream.range(0, capacity).mapToObj(location -> new Box(lockerNumber, location + 1)).collect(Collectors.toList());

    }

    public Ticket saveBox() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            availableBox.setAvailable(false);
            availableCount--;
            return ticketManager.generateTicket(availableBox);
        }
        return null;
    }

    private Box getAvailableBox() {
        return boxList.stream().filter(Box::isAvailable).findAny().orElse(null);
    }

    public Box unLockBox(Ticket ticket) {
        Box box = ticketManager.verifyTicket(ticket);
        if (box != null) {
            box.setAvailable(true);
            availableCount++;
        }
        return box;
    }

    public Boolean isAvailable() {
        return availableCount > 0;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public int getCapacity() {
        return capacity;
    }
}

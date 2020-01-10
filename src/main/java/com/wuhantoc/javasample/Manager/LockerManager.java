package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockerManager {

    private final int capacity = 24;
    private int availableCount = capacity;
    private final int lockerNumber;
    private final Map<String, Box> boxTicketMap = new HashMap<>();

    private final List<Box> boxes;

    public LockerManager(int lockerNumber) {
        this.lockerNumber = lockerNumber;
        boxes = IntStream.range(0, capacity)
            .mapToObj(location -> new Box(lockerNumber, location + 1))
            .collect(Collectors.toList());

    }

    public Ticket savePackage() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            availableBox.setAvailable(false);
            availableCount--;
            Ticket ticket = generateTicket(availableBox);
            boxTicketMap.put(ticket.getCode(), availableBox);
            return ticket;
        }
        return null;
    }

    public Box getPackage(Ticket ticket) {
        Box box = boxTicketMap.remove(ticket.getCode());
        if (box != null) {
            box.setAvailable(true);
            availableCount++;
        }
        return box;
    }

    public Boolean isAvailable() {
        return availableCount > 0;
    }

    private Ticket generateTicket(Box availableBox) {
        String code = String.format("%s%04d", UUID.randomUUID().toString(), this.lockerNumber);
        return new Ticket(availableBox.getLocation(), this.lockerNumber, code);
    }

    private Box getAvailableBox() {
        return boxes.stream().filter(Box::isAvailable).findAny().orElse(null);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }
}

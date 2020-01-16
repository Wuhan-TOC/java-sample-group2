package com.wuhantoc.javasample.locker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Locker {

    private static final String PARAM_INVALID = "capacity can't lower than zero";
    private final int capacity;
    private final int lockerNumber;
    private final Map<String, Box> codeBoxMap = new HashMap<>();

    private final List<Box> boxes;

    public Locker(int lockerNumber, int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException(PARAM_INVALID);
        }
        this.capacity = capacity;
        this.lockerNumber = lockerNumber;
        boxes = IntStream.range(0, capacity)
            .mapToObj(boxNumber -> new Box(lockerNumber, boxNumber + 1))
            .collect(Collectors.toList());
    }

    public Ticket savePackage(Bag bag) {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            availableBox.setBag(bag);
            Ticket ticket = generateTicket(availableBox);
            codeBoxMap.put(ticket.getCode(), availableBox);
            return ticket;
        }
        return null;
    }

    public Bag getPackage(Ticket ticket) {
        Box box = codeBoxMap.remove(ticket.getCode());
        if (box != null) {
            return box.getBag();
        }
        return null;
    }

    public double getVacancyRate() {
        return  (double) (capacity - codeBoxMap.size()) / (double) capacity;
    }

    private Ticket generateTicket(Box availableBox) {
        String code = String.format("%s%04d", UUID.randomUUID().toString(), this.lockerNumber);
        return new Ticket(availableBox.getBoxNumber(), this.lockerNumber, code);
    }

    private Box getAvailableBox() {
        return boxes.stream().filter(box -> box.getBag() == null).findAny().orElse(null);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLockerNumber() {
        return lockerNumber;
    }

}

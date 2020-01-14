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


    private static final String PARAM_INVALID = "capacity can't lower than zero";
    private final int capacity;
    private int availableCount;
    private final int lockerNumber;
    private double vacancyRate;
    private final Map<String, Box> codeBoxMap = new HashMap<>();

    private final List<Box> boxes;

    public LockerManager(int lockerNumber, int capacity) {
        if (capacity <= 0d) {
            throw new RuntimeException(PARAM_INVALID);
        }
        this.capacity = capacity;
        this.lockerNumber = lockerNumber;
        this.availableCount = capacity;
        this.vacancyRate = (double) availableCount / (double) capacity;
        boxes = IntStream.range(0, capacity)
            .mapToObj(boxNumber -> new Box(lockerNumber, boxNumber + 1))
            .collect(Collectors.toList());
    }

    public Ticket savePackage() {
        Box availableBox = getAvailableBox();
        if (availableBox != null) {
            occupyBox(availableBox);
            Ticket ticket = generateTicket(availableBox);
            codeBoxMap.put(ticket.getCode(), availableBox);
            return ticket;
        }
        return null;
    }

    public Box getPackage(Ticket ticket) {
        Box box = codeBoxMap.remove(ticket.getCode());
        releaseBox(box);
        return box;
    }


    private void occupyBox(Box box) {
        if (box != null) {
            box.setAvailable(false);
            availableCount--;
            vacancyRate = (double) availableCount / (double) capacity;
        }
    }

    private void releaseBox(Box box) {
        if (box != null) {
            box.setAvailable(true);
            availableCount++;
            vacancyRate = (double) availableCount / (double) capacity;
        }
    }

    public Boolean isAvailable() {
        return availableCount > 0;
    }

    private Ticket generateTicket(Box availableBox) {
        String code = String.format("%s%04d", UUID.randomUUID().toString(), this.lockerNumber);
        return new Ticket(availableBox.getBoxNumber(), this.lockerNumber, code);
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

    public double getVacancyRate() {
        return vacancyRate;
    }
}

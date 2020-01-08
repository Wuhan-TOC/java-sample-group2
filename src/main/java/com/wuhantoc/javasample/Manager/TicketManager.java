package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TicketManager {

    private Map<String, Box> boxMap = new HashMap<>();

    public Ticket generateTicket(Box box) {
        String scannerCode = UUID.randomUUID().toString();
        boxMap.put(scannerCode, box);
        return new Ticket(box.getLocation(), box.getLockNumber(), scannerCode);
    }

    public Box verifyTicket(Ticket ticket) {
        Box box = boxMap.get(ticket.getScannerCode());
        if (box != null) {
            boxMap.remove(ticket.getScannerCode());
        }
        return box;
    }

}

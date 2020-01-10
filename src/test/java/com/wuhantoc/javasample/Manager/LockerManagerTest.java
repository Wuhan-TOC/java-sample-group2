package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LockerManagerTest {

    @Test
    void should_return_not_null_ticket_and_empty_box_quantity_reduce_1_when_have_available_box() {
        //given 24 available boxes
        LockerManager lockerManager = new LockerManager(1,24);
        //When
        Ticket ticket = lockerManager.savePackage();
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(23, availableBoxCount);
    }

    @Test
    void should_return_null_when_saving_but_have_not_available_box() {
        //given 0 available box
        LockerManager lockerManager = new LockerManager(1,0);
        fullAllBox(lockerManager);

        //when
        Ticket scannerCode = lockerManager.savePackage();
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertNull(scannerCode);
        Assertions.assertEquals(0, availableBoxCount);
    }

    @Test
    void should_return_box_and_available_box_count_add_1_when_given_valid_scanner_code() {

        //given 23 available boxes
        LockerManager lockerManager = new LockerManager(1,24);
        Ticket ticket = lockerManager.savePackage();

        //when
        Box box = lockerManager.getPackage(ticket);
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertNotNull(box);
        Assertions.assertTrue(box.isAvailable());
        Assertions.assertEquals(24, availableBoxCount);

    }

    @Test
    void should_return_null_when_given_invalid_scanner_code() {
        //given
        LockerManager lockerManager = new LockerManager(1,24);
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Box box = lockerManager.getPackage(ticket);

        //then
        Assertions.assertNull(box);

    }

    private void fullAllBox(LockerManager lockerManager) {
        for (int i = 0; i < lockerManager.getCapacity(); i++) {
            lockerManager.savePackage();
        }
    }
}

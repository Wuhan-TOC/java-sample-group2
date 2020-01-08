package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LockerManagerTest {

    private LockerManager lockerManager = new LockerManager();

    @Test
    void should_return_not_null_ticket_and_empty_box_quantity_reduce_1_when_have_available_box() {
        //given 24 available boxes

        //When
        Ticket ticket = lockerManager.saveBox();
        int availableBoxCount = getAvailableBoxCount();

        //then
        Assertions.assertNotNull(ticket);
        Assertions.assertEquals(23, availableBoxCount);
    }

    @Test
    void should_return_null_when_saving_but_have_not_available_box() {
        //given 0 available box
        fullAllBox();

        //when
        Ticket scannerCode = lockerManager.saveBox();
        int availableBoxCount = getAvailableBoxCount();

        //then
        Assertions.assertNull(scannerCode);
        Assertions.assertEquals(0, availableBoxCount);
    }

    @Test
    void should_return_box_and_available_box_count_add_1_when_given_valid_scanner_code() {
        //given 23 available boxes
        Ticket ticket = lockerManager.saveBox();

        //when
        Box box = lockerManager.unLockBox(ticket.getScannerCode());
        int availableBoxCount = getAvailableBoxCount();

        //then
        Assertions.assertNotNull(box);
        Assertions.assertTrue(box.isAvailable());
        Assertions.assertEquals(24, availableBoxCount);

    }

    @Test
    void should_return_null_when_given_invalid_scanner_code() {
        //given
        String scannerCode = "123";

        //when
        Box box = lockerManager.unLockBox(scannerCode);

        //then
        Assertions.assertNull(box);

    }

    private int getAvailableBoxCount() {
        return (int) lockerManager.getBoxList().stream().filter(Box::isAvailable).count();
    }

    private void fullAllBox() {
        List<Box> boxList = lockerManager.getBoxList();

        List<Box> fullBoxList = boxList.stream().peek(box -> box.setAvailable(false)).collect(Collectors.toList());

        lockerManager.setBoxList(fullBoxList);
    }
}

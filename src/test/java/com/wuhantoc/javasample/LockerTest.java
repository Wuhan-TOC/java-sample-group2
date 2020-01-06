package com.wuhantoc.javasample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LockerTest {

    private Locker locker = new Locker();

    @Test
    void should_return_true_when_have_available_box() {

        //when
        boolean availableFlag = locker.isAvailable();

        //then
        Assertions.assertTrue(availableFlag);
    }

    @Test
    void should_return_not_null_scanner_code_and_empty_box_quantity_reduce_1_when_have_available_box() {

        //When
        String scannerCode = locker.saveBox();
        int availableBoxCount = locker.getAvailableBoxCount();

        //then
        Assertions.assertNotNull(scannerCode);
        Assertions.assertEquals(23, availableBoxCount);
    }

    @Test
    void should_return_null_when_saving_but_have_not_available_box() {

        //when
        fullAllBox();
        String scannerCode = locker.saveBox();
        int availableBoxCount = locker.getAvailableBoxCount();

        //then
        Assertions.assertNull(scannerCode);
        Assertions.assertEquals(0, availableBoxCount);
    }

    @Test
    void should_return_box_and_available_box_count_add_1_when_given_valid_scanner_code() {
        //given 23 available box
        String scannerCode = locker.saveBox();

        //when
        Box box = locker.unLockBox(scannerCode);
        int availableBoxCount = locker.getAvailableBoxCount();

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
        Box box = locker.unLockBox(scannerCode);

        //then
        Assertions.assertNull(box);

    }

    private void fullAllBox() {
        List<Box> boxList = locker.getBoxList();

        List<Box> fullBoxList = boxList.stream().peek(box -> box.setAvailable(false)).collect(Collectors.toList());

        locker.setBoxList(fullBoxList);
    }
}

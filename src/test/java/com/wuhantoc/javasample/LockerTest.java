package com.wuhantoc.javasample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LockerTest {

     private Locker locker =  new Locker();

    @Test
    void should_return_true_when_have_available_box (){

        //when
        boolean availableFlag = locker.isAvailable();

        //then
        Assertions.assertTrue(availableFlag);
    }

    @Test
    void should_return_not_null_scanner_code_and_empty_box_quantity_reduce_1_when_have_available_box () {

        //When
        String scannerCode = locker.getScannerCode();
        Long emptyBoxNum = locker.getBoxList().stream().filter(Box::isAvailable).count();

        //then
        Assertions.assertNotNull(scannerCode);
        Assertions.assertEquals(23, emptyBoxNum.intValue());
    }

    @Test
    void should_return_not_null_scanner_code_and_empty_box_quantity_reduce_1_when_saving_and_have_available_box () {

        //when
        String scannerCode = locker.lockBox();
        Long emptyBoxNum = locker.getBoxList().stream().filter(Box::isAvailable).count();

        //then
        Assertions.assertNotNull(scannerCode);
        Assertions.assertEquals(23, emptyBoxNum.intValue());
    }

    @Test
    void should_return_null_when_saving_but_have_not_available_box () {

        //when
        fullAllBox();
        String scannerCode = locker.lockBox();
        Long usedBoxNum = locker.getBoxList().stream().filter(box -> !box.isAvailable()).count();

        //then
        Assertions.assertNull(scannerCode);
        Assertions.assertEquals(24, usedBoxNum);
    }

    @Test
    void should_return_box_info_when_given_valid_scanner_code() {
        //given
        String scannerCode = locker.lockBox();

        //when
        Box box = locker.unLockBox(scannerCode);

        //then
        Assertions.assertNotNull(box);
        Assertions.assertTrue(box.isAvailable());

    }

    @Test
    void should_return_null_when_given_invalid_scanner_code() {
        //given
        String scannerCode = String.valueOf(System.currentTimeMillis()).concat("1111");

        //when
        Box box = locker.unLockBox(scannerCode);

        //then
        Assertions.assertNull(box);

    }

    private void fullAllBox () {
        List<Box> boxList = locker.getBoxList();

        List<Box> fullBoxList = boxList.stream().map(box -> {
            box.setScannerCode();
            box.setAvailable(false);
            return box;
        }).collect(Collectors.toList());

        locker.setBoxList(fullBoxList);
    }
}

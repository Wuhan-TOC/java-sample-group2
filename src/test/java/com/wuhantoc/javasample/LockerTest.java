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
    void should_return_not_null_scanner_code_when_have_available_box () {

        //When
        String scannerCode = locker.getScannerCode();

        //then
        Assertions.assertNotNull(scannerCode);
    }

    @Test
    void should_return_not_null_scanner_code_when_input_saving_and_have_available_box () {

        //when
        String scannerCode = locker.useLocker("save");

        //then
        Assertions.assertNotNull(scannerCode);
    }

    @Test
    void should_return_null_scanner_code_when_input_saving_but_have_not_available_box () {

        //when
        fullAllBox();
        String scannerCode = locker.useLocker("save");

        //then
        Assertions.assertNull(scannerCode);
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

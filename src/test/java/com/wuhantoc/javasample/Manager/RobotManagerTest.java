package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;


class RobotManagerTest {

    RobotManager robotManager = new RobotManager();

    @Test
    void should_return_locker_manager_when_find_locker_by_locker_number_given_valid_locker_number() {
        //given
        Integer lockerNumber = 1;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        Assertions.assertNotNull(lockerManager);
    }


    @Test
    void should_return_locker_manager_when_find_locker_by_locker_number_given_invalid_locker_number() {
        //given
        Integer lockerNumber = 11;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        Assertions.assertNull(lockerManager);
    }

    @Test
    void should_return_first_not_empty_Locker_Manger_when_find_first_not_empty_locker_given_an_not_empty_locker_collectors() {
        //given
        fullLocker(1);

        LockerManager lockerManager = robotManager.findFirstNotEmptyLockerManger();

        Assertions.assertNotNull(lockerManager);
        Assertions.assertEquals(2, lockerManager.getLockerNumber());
        Assertions.assertTrue(lockerManager.isAvailable());
    }

    private void fullLocker(Integer lockerNumber) {
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);
        List<LockerManager> lockerManagerList = robotManager.getLockerManagerList();
        List<Box> boxList = lockerManager.getBoxList();

        List<Box> fullBoxList = boxList.stream().peek(box -> box.setAvailable(false)).collect(Collectors.toList());

        lockerManager.setBoxList(fullBoxList);
        lockerManagerList.set(lockerNumber - 1, lockerManager);
    }
}


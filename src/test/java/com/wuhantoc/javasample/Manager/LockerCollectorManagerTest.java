package com.wuhantoc.javasample.Manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LockerCollectorManagerTest {

    @Test
    void should_return_locker_manager_when_find_locker_by_locker_number_given_valid_locker_number() {
        //given
        Integer lockerNumber = 1;
        LockerCollectorManager lockerCollectorManager = new LockerCollectorManager();

        //when
        LockerManager lockerManager = lockerCollectorManager.findLockerByNumber(lockerNumber);

        Assertions.assertNotNull(lockerManager);
    }
}


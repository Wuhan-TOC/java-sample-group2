package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Ticket;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuperRobotManagerTest {

    @Test
    public void should_store_in_locker1_when_save_package_given_two_empty_locker() {
        // given
        SuperRobotManager superRobotManager = new SuperRobotManager(2, Arrays.asList(2, 2));

        // when
        Ticket ticket = superRobotManager.savePackage();

        //than
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());
    }

    @Test
    public void should_store_in_locker2_when_save_package_given_locker1_empty_50_and_locker2_empty_100 () {
        // given
        SuperRobotManager superRobotManager = new SuperRobotManager(2, Arrays.asList(2, 2));
        superRobotManager.savePackage();

        // when
        Ticket ticket = superRobotManager.savePackage();

        // then
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    public void should_return_null_when_save_package_given_locker1_empty_0_and_locker2_empty_0 () {
        // given
        SuperRobotManager superRobotManager = new SuperRobotManager(2, Arrays.asList(2, 2));
        superRobotManager.savePackage();
        superRobotManager.savePackage();
        superRobotManager.savePackage();
        superRobotManager.savePackage();

        // when
        Ticket ticket = superRobotManager.savePackage();

        // then
        assertNull(ticket);
    }

}

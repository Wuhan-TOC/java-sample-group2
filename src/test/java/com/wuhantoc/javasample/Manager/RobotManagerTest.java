package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class RobotManagerTest {


    @Test
    void should_return_locker_manager_when_find_locker_by_locker_number_given_valid_locker_number() {
        //given
        RobotManager robotManager = new RobotManager();
        Integer lockerNumber = 1;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        assertNotNull(lockerManager);
    }


    @Test
    void should_return_locker_manager_when_find_locker_by_locker_number_given_invalid_locker_number() {
        //given
        RobotManager robotManager = new RobotManager();
        Integer lockerNumber = 11;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        assertNull(lockerManager);
    }

    @Test
    void should_return_first_not_empty_Locker_Manger_when_find_first_not_empty_locker_given_an_not_empty_locker_collectors() {
        //given
        RobotManager robotManager = new RobotManager();
        fullLocker(robotManager, 1);

        LockerManager lockerManager = robotManager.findFirstNotEmptyLockerManger();

        assertNotNull(lockerManager);
        assertEquals(2, lockerManager.getLockerNumber());
        assertTrue(lockerManager.isAvailable());
    }


    @Test
    void should_get_not_null_ticket_and_locker_number_is_1_when_robot_save_package_given_10_empty_lockers() {
        //given
        RobotManager robotManager = new RobotManager();

        //when
        Ticket ticket = robotManager.saveBox();

        //then
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());

    }

    @Test
    void should_get_not_null_ticket_and_locker_number_is_2_when_robot_save_package_given_first_locker_is_full_and_second_is_empty() {
        //given
        RobotManager robotManager = new RobotManager();
        fullLocker(robotManager, 1);

        //when
        Ticket ticket = robotManager.saveBox();

        //then
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    void should_get_null_ticket_when_robot_save_package_given_10_full_lockers() {
        //given
        RobotManager robotManager = new RobotManager();
        fullAllLockers(robotManager);

        //when
        Ticket ticket = robotManager.saveBox();

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_correct_box_when_robot_unlock_box_given_valid_ticket() {
        //given
        RobotManager robotManager = new RobotManager();
        Ticket ticket = robotManager.saveBox();

        //when
        Box box = robotManager.unLock(ticket);

        //then
        assertNotNull(box);
        assertEquals(ticket.getLockerNumber(), box.getLockNumber());
        assertEquals(ticket.getBoxLocation(), box.getLocation());
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_invalid_ticker() {
        //given
        RobotManager robotManager = new RobotManager();
        Ticket ticket = new Ticket(1, 1, "123");

        //when
        Box box = robotManager.unLock(ticket);

        //then
        assertNull(box);
    }

    private void fullAllBox(LockerManager lockerManager) {
        IntStream.range(0, lockerManager.getCapacity()).forEach(index -> lockerManager.saveBox());
    }



    private void fullLocker(RobotManager robotManager, Integer lockerNumber) {
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);
        fullAllBox(lockerManager);
    }

    private void fullAllLockers(RobotManager robotManager) {
        IntStream.range(0, robotManager.getLockerSum()).forEach(index -> fullLocker(robotManager, index + 1));
    }
}


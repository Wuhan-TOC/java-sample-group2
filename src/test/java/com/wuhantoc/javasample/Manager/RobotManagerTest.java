package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class RobotManagerTest {

    public final int lockerSum = 4;
    private final List<Integer> capacities = Arrays.asList(24, 24, 24, 24);

    @Test
    void should_return_locker1_when_find_locker_by_locker_number_given_4_lockers_locker_number_equals_1() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        Integer lockerNumber = 1;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        //then
        assertNotNull(lockerManager);
    }


    @Test
    void should_return_null_when_find_locker_by_locker_number_given_4_lockers_locker_number_equals_11() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        Integer lockerNumber = 11;

        //when
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);

        //then
        assertNull(lockerManager);
    }

    @Test
    void should_return_locker2_when_find_first_not_empty_locker_given_4_lockers_locker1_full_locker_2_empty() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        fullLocker(robotManager, 1);

        //then
        LockerManager lockerManager = robotManager.findFirstNotEmptyLockerManger();

        //then
        assertEquals(2, lockerManager.getLockerNumber());
    }


    @Test
    void should_get_not_null_ticket_and_locker_number_is_1_when_robot_save_package_given_4_empty_lockers() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);

        //when
        Ticket ticket = robotManager.savePackage();

        //then
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());

    }

    @Test
    void should_get_not_null_ticket_and_locker_number_is_2_when_robot_save_package_given_4_lockers_locker1_is_full_and_locker2_is_empty() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        fullLocker(robotManager, 1);

        //when
        Ticket ticket = robotManager.savePackage();

        //then
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    void should_get_null_ticket_when_robot_save_package_given_10_full_lockers() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        fullAllLockers(robotManager);

        //when
        Ticket ticket = robotManager.savePackage();

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_correct_box_when_robot_unlock_box_given_valid_ticket() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        Ticket ticket = robotManager.savePackage();

        //when
        Box box = robotManager.getPackage(ticket);

        //then
        assertNotNull(box);
        assertEquals(ticket.getLockerNumber(), box.getLockerNumber());
        assertEquals(ticket.getBoxLocation(), box.getBoxNumber());
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_fake_ticker() {
        //given
        RobotManager robotManager = new RobotManager(lockerSum, capacities);
        Ticket ticket = new Ticket(1, 1, "123");

        //when
        Box box = robotManager.getPackage(ticket);

        //then
        assertNull(box);
    }

    private void fullAllBox(LockerManager lockerManager) {
        IntStream.range(0, lockerManager.getCapacity())
            .forEach(index -> lockerManager.savePackage());
    }


    private void fullLocker(RobotManager robotManager, Integer lockerNumber) {
        LockerManager lockerManager = robotManager.findLockerByNumber(lockerNumber);
        fullAllBox(lockerManager);
    }

    private void fullAllLockers(RobotManager robotManager) {
        IntStream.range(0, robotManager.getLockerSum())
            .forEach(index -> fullLocker(robotManager, index + 1));
    }
}


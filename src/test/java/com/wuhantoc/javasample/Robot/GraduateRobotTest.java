package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Box;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class GraduateRobotTest {

    @Test
    void should_return_locker1_when_find_locker_by_locker_number_given_2_lockers_locker_number_equals_1() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Integer lockerNumber = 1;

        //when
        Locker locker = graduateRobot.findLockerByNumber(lockerNumber);

        //then
        assertNotNull(locker);
    }


    @Test
    void should_return_null_when_find_locker_by_locker_number_given_2_lockers_locker_number_equals_11() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Integer lockerNumber = 11;

        //when
        Locker locker = graduateRobot.findLockerByNumber(lockerNumber);

        //then
        assertNull(locker);
    }

    @Test
    void should_return_locker2_when_find_first_not_empty_locker_given_2_lockers_locker1_full_locker_2_empty() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        fullLocker(locker1);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //then
        Locker locker = graduateRobot.findFirstNotEmptyLockerManger();

        //then
        assertEquals(2, locker.getLockerNumber());
    }


    @Test
    void should_get_not_null_ticket_and_locker_number_is_1_when_robot_save_package_given_2_empty_lockers() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //when
        Ticket ticket = graduateRobot.savePackage();

        //then
        assertNotNull(ticket);
        assertEquals(ticket.getLockerNumber(), ticket.getLockerNumber());
        assertEquals(1, ticket.getLockerNumber());

    }

    @Test
    void should_get_not_null_ticket_and_locker_number_is_2_when_robot_save_package_given_2_lockers_locker1_is_full_and_locker2_is_empty() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        fullLocker(locker1);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //when
        Ticket ticket = graduateRobot.savePackage();

        //then
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    void should_get_null_ticket_when_robot_save_package_given_2_full_lockers() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        fullLocker(locker1);
        fullLocker(locker2);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //when
        Ticket ticket = graduateRobot.savePackage();

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_correct_box_when_robot_unlock_box_given_valid_ticket() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = graduateRobot.savePackage();

        //when
        Box box = graduateRobot.getPackage(ticket);

        //then
        assertNotNull(box);
        assertEquals(ticket.getLockerNumber(), box.getLockerNumber());
        assertEquals(ticket.getBoxLocation(), box.getBoxNumber());
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_fake_ticker() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = new Ticket(1, 1, "123");

        //when
        Box box = graduateRobot.getPackage(ticket);

        //then
        assertNull(box);
    }

    private void fullLocker(Locker locker) {
        IntStream.range(0, locker.getCapacity())
            .forEach(index -> locker.savePackage());
    }
}


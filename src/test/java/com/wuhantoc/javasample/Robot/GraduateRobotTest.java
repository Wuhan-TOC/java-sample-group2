package com.wuhantoc.javasample.Robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.wuhantoc.javasample.Locker.Bag;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;


class GraduateRobotTest {

    @Test
    void should_return_locker2_when_find_first_not_empty_locker_given_2_lockers_locker1_full_locker_2_empty() {
        //given
        Locker locker1 = getFullLocker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //then
        Locker locker = graduateRobot.findLockerToSave();

        //then
        assertEquals(2, locker.getLockerNumber());
    }


    @Test
    void should_get_not_null_ticket_and_locker_number_is_1_when_robot_save_package_given_2_empty_lockers() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        //when
        Ticket ticket = graduateRobot.savePackage(bag);

        //then
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());

    }

    @Test
    void should_get_not_null_ticket_and_locker_number_is_2_when_robot_save_package_given_2_lockers_locker1_is_full_and_locker2_is_empty() {
        //given
        Locker locker1 = getFullLocker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        //when
        Ticket ticket = graduateRobot.savePackage(bag);

        //then
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    void should_get_null_ticket_when_robot_save_package_given_2_full_lockers() {
        //given
        Locker locker1 = getFullLocker(1, 24);
        Locker locker2 = getFullLocker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));

        //when
        Ticket ticket = graduateRobot.savePackage(new Bag());

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_correct_bag_when_robot_unlock_box_given_valid_ticket() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Bag savedBag = new Bag();
        Ticket ticket = graduateRobot.savePackage(savedBag);

        //when
        Bag gotBag = graduateRobot.getPackage(ticket);

        //then
        assertEquals(savedBag, gotBag);
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_fake_ticker() {
        //given
        Locker locker1 = new Locker(1, 24);
        Locker locker2 = new Locker(2, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = new Ticket(1, 1, "123");

        //when
        Bag bag = graduateRobot.getPackage(ticket);

        //then
        assertNull(bag);
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_used_ticker() {
        //given
        Locker locker1 = new Locker(1, 24);
        GraduateRobot graduateRobot = new GraduateRobot(Collections.singletonList(locker1));
        Ticket ticket = graduateRobot.savePackage(new Bag());
        graduateRobot.getPackage(ticket);

        //when
        Bag bag = graduateRobot.getPackage(ticket);

        //then
        assertNull(bag);
    }

    private Locker getFullLocker(int lockerNumber, int capacity) {
        Locker locker = new Locker(lockerNumber, capacity);
        for (int i = 0; i < locker.getCapacity(); i++) {
            locker.savePackage(new Bag());
        }
        return locker;
    }
}


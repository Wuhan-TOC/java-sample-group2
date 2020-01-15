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

class SuperRobotTest {

    @Test
    void should_store_in_locker1_when_save_package_given_locker1_has_2_capacity_no_box_used_and_locker2_has_2_capacity_no_box_used() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));

        // when
        Ticket ticket = superRobot.savePackage(new Bag());

        //than
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());
    }

    @Test
    void should_store_in_locker1_when_save_package_given_locker1_has_2_capacity_1_box_used_and_locker2_has_6_capacity_3_box_used() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 6);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Locker locker = superRobot.getLockers().get(1);
        locker.savePackage(new Bag());
        locker.savePackage(new Bag());
        locker.savePackage(new Bag());

        // when
        Ticket ticket = superRobot.savePackage(new Bag());

        //than
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());
    }

    @Test
    void should_store_in_locker2_when_save_package_given_locker1_has_4_capacity_1_box_be_used_and_locker2_has_2_capacity_no_box_used() {
        // given
        Locker locker1 = new Locker(1, 4);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        superRobot.getLockers().get(0).savePackage(new Bag());

        //when
        Ticket ticket = superRobot.savePackage(new Bag());


        //than
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    void should_return_null_when_save_package_given_locker1_has_1_capacity_1_used_and_locker2_has_1_capacity_1_box_used () {
        // given
        Locker locker1 = new Locker(1, 1);
        Locker locker2 = new Locker(2, 1);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        superRobot.getLockers().get(0).savePackage(new Bag());
        superRobot.getLockers().get(1).savePackage(new Bag());

        // when
        Ticket ticket = superRobot.savePackage(new Bag());

        // then
        assertNull(ticket);
    }

    @Test
    void should_return_box_when_given_valid_scanner_code() {
        // given
        Locker locker1 = new Locker(1, 4);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Bag savedBag = new Bag();
        Ticket ticket = superRobot.savePackage(savedBag);

        //when
        Bag gotBag = superRobot.getPackage(ticket);

        //then
        assertEquals(gotBag, savedBag);
    }

    @Test
    void should_return_null_when_given_fake_scanner_code() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Bag bag = superRobot.getPackage(ticket);

        //then
        assertNull(bag);
    }

    @Test
    void should_return_null_when_robot_unlock_box_given_used_ticker() {
        //given
        Locker locker1 = new Locker(1, 24);
        SuperRobot superRobot = new SuperRobot(Collections.singletonList(locker1));
        Ticket ticket = superRobot.savePackage(new Bag());
        superRobot.getPackage(ticket);

        //when
        Bag bag = superRobot.getPackage(ticket);

        //then
        assertNull(bag);
    }

}

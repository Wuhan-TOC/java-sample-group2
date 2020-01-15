package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Box;
import com.wuhantoc.javasample.Locker.Locker;
import com.wuhantoc.javasample.Locker.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SuperRobotTest {

    @Test
    public void should_store_in_locker1_when_save_package_given_locker1_has_2_capacity_no_box_used_and_locker2_has_2_capacity_no_box_used() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));

        // when
        Ticket ticket = superRobot.savePackage();

        //than
        assertNotNull(ticket);
        assertEquals(1, ticket.getLockerNumber());
    }

    @Test
    public void should_store_in_locker1_when_save_package_given_locker1_has_2_capacity_1_box_used_and_locker2_has_6_capacity_3_box_used() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 6);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Locker locker = superRobot.getLockers().get(1);
        locker.savePackage();
        locker.savePackage();
        locker.savePackage();

        // when
        Ticket ticket = superRobot.savePackage();

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
        superRobot.getLockers().get(0).savePackage();

        //when
        Ticket ticket = superRobot.savePackage();


        //than
        assertNotNull(ticket);
        assertEquals(2, ticket.getLockerNumber());
    }

    @Test
    public void should_return_null_when_save_package_given_locker1_has_1_capacity_1_used_and_locker2_has_1_capacity_1_box_used () {
        // given
        Locker locker1 = new Locker(1, 1);
        Locker locker2 = new Locker(2, 1);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        superRobot.getLockers().get(0).savePackage();
        superRobot.getLockers().get(1).savePackage();

        // when
        Ticket ticket = superRobot.savePackage();

        // then
        assertNull(ticket);
    }

    @Test
    void should_return_box_when_given_valid_scanner_code() {
        // given
        Locker locker1 = new Locker(1, 4);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = superRobot.savePackage();

        //when
        Box box = superRobot.getPackage(ticket);

        //then
        assertNotNull(box);
        assertTrue(box.isAvailable());
    }

    @Test
    void should_return_null_when_given_fake_scanner_code() {
        // given
        Locker locker1 = new Locker(1, 2);
        Locker locker2 = new Locker(2, 2);
        SuperRobot superRobot = new SuperRobot(Arrays.asList(locker1, locker2));
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Box box = superRobot.getPackage(ticket);

        //then
        assertNull(box);
    }

}

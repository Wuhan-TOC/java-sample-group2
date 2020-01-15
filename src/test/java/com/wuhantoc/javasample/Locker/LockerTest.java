package com.wuhantoc.javasample.Locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LockerTest {

    @Test
    void should_store_locker1_and_vacancy_rate_equals_23_out_of_24_when_save_package_given_24_capacity_and_no_box_used_locker() {
        //given 24 available boxes
        Locker locker = new Locker(1, 24);
        Bag bag = new Bag();

        //When
        Ticket ticket = locker.savePackage(bag);
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertEquals(1, ticket.getLockerNumber());
        Assertions.assertEquals(23 / 24d, vacancyRate);
    }

    @Test
    void should_get_null_and_vacancy_rate_equals_0_out_of_2_when_save_package_given_2_capacity_and_2_used_locker() {
        //given 0 available box
        Locker locker = getFullLocker(1,2);
        Bag bag = new Bag();

        //when
        Ticket ticket = locker.savePackage(bag);
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertNull(ticket);
        Assertions.assertEquals(0/2d, vacancyRate);
    }

    @Test
    void should_get_right_bag_and_vacancy_rate_equals_24_out_of_24_when_get_package_given_valid_ticket_and_24_capacity_1_box_used_locker() {
        //given 23 available boxes
        Locker locker = new Locker(1,24);
        Bag savedBag = new Bag();
        Ticket ticket = locker.savePackage(savedBag);

        //when
        Bag gotBag = locker.getPackage(ticket);
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertEquals(gotBag, savedBag);
        Assertions.assertEquals(24/24d, vacancyRate);

    }

    @Test
    void should_return_null_when_get_package_given_fake_ticket() {
        //given
        Locker locker = new Locker(1,24);
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Bag bag = locker.getPackage(ticket);

        //then
        Assertions.assertNull(bag);

    }

    @Test
    void should_return_null_when_get_package_given_used_ticket() {
        //given
        Locker locker = new Locker(1,24);
        Ticket ticket = locker.savePackage(new Bag());
        locker.getPackage(ticket);

        //when
        Bag bag = locker.getPackage(ticket);

        //then
        Assertions.assertNull(bag);

    }

    @Test
    void should_vacancy_rate_equal_50_percent_when_get_vacancy_rate_given_2_capacity_1_box_used_locker() {
        // given
        Locker locker = new Locker(1, 2);
        Bag bag = new Bag();
        locker.savePackage(bag);

        // when
        double vacancyRate = locker.getVacancyRate();

        // than
        Assertions.assertEquals(1/2d, vacancyRate);
    }

    private Locker getFullLocker(int lockerNumber, int capacity) {
        Locker locker = new Locker(lockerNumber, capacity);
        for (int i = 0; i < locker.getCapacity(); i++) {
            locker.savePackage(new Bag());
        }
        return locker;
    }
}

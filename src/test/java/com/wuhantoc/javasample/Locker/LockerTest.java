package com.wuhantoc.javasample.Locker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LockerTest {

    @Test
    void should_get_locker_number_equals_1_ticket_and_vacancy_rate_equals_23_out_of_24_when_save_package_given_locker_24_capacity_no_box_used() {
        //given 24 available boxes
        Locker locker = new Locker(1,24);

        //When
        Ticket ticket = locker.savePackage();
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertEquals(1, ticket.getLockerNumber());
        Assertions.assertEquals(23/24d, vacancyRate);
    }

    @Test
    void should_get_null_and_vacancy_rate_equals_0_out_of_1_when_save_package_given_locker_0_capacity() {
        //given 0 available box
        Locker locker = new Locker(1,1);
        fullAllBox(locker);

        //when
        Ticket ticket = locker.savePackage();
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertNull(ticket);
        Assertions.assertEquals(0/1d, vacancyRate);
    }

    @Test
    void should_get_status_is_available_box_and_vacancy_rate_equals_24_out_of_24_when_get_package_given_given_valid_ticket_and_locker_24_capacity_1_box_used() {
        //given 23 available boxes
        Locker locker = new Locker(1,24);
        Ticket ticket = locker.savePackage();

        //when
        Box box = locker.getPackage(ticket);
        double vacancyRate = locker.getVacancyRate();

        //then
        Assertions.assertNotNull(box);
        Assertions.assertTrue(box.isAvailable());
        Assertions.assertEquals(1/1d, vacancyRate);

    }

    @Test
    void should_return_null_when_get_package_given_fake_ticket() {
        //given
        Locker locker = new Locker(1,24);
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Box box = locker.getPackage(ticket);

        //then
        Assertions.assertNull(box);

    }

    @Test
    void should_vacancy_rate_equal_50_percent_when_get_vacancy_rate_given_locker_2_capacity_1_box_used() {
        // given
        Locker locker = new Locker(1, 2);
        locker.savePackage();

        // when
        double vacancyRate = locker.getVacancyRate();

        // than
        Assertions.assertEquals(1/2d, vacancyRate);
    }

    private void fullAllBox(Locker locker) {
        for (int i = 0; i < locker.getCapacity(); i++) {
            locker.savePackage();
        }
    }
}

package com.wuhantoc.javasample.Manager;

import com.wuhantoc.javasample.entity.Box;
import com.wuhantoc.javasample.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LockerManagerTest {

    @Test
    void should_return_ticket_has_correct_locker_number_and_empty_box_quantity_reduce_1_when_locker_save_package_given_locker_24_capacity_no_box_used() {
        //given 24 available boxes
        LockerManager lockerManager = new LockerManager(1,24);

        //When
        Ticket ticket = lockerManager.savePackage();
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertEquals(1, ticket.getLockerNumber());
        Assertions.assertEquals(23, availableBoxCount);
    }

    @Test
    void should_return_null_when_save_package_given_locker_0_capacity() {
        //given 0 available box
        LockerManager lockerManager = new LockerManager(1,1);
        fullAllBox(lockerManager);

        //when
        Ticket scannerCode = lockerManager.savePackage();
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertNull(scannerCode);
        Assertions.assertEquals(0, availableBoxCount);
    }

    @Test
    void should_return_one_available_box_and_available_box_count_add_1_when_get_package_given_valid_ticket() {
        //given 23 available boxes
        LockerManager lockerManager = new LockerManager(1,24);
        Ticket ticket = lockerManager.savePackage();

        //when
        Box box = lockerManager.getPackage(ticket);
        int availableBoxCount = lockerManager.getAvailableCount();

        //then
        Assertions.assertNotNull(box);
        Assertions.assertTrue(box.isAvailable());
        Assertions.assertEquals(24, availableBoxCount);

    }

    @Test
    void should_return_null_when_get_package_given_fake_ticket() {
        //given
        LockerManager lockerManager = new LockerManager(1,24);
        Ticket ticket = new Ticket(1,2,"123");

        //when
        Box box = lockerManager.getPackage(ticket);

        //then
        Assertions.assertNull(box);

    }

    @Test
    void should_vacancy_rate_equal_50_percent_get_vacancy_rate_given_locker_2_capacity_1_box_used() {
        // given
        LockerManager lockerManager = new LockerManager(1, 2);
        lockerManager.savePackage();

        // when
        double vacancyRate = lockerManager.getVacancyRate();

        // than
        Assertions.assertEquals(0.5d, vacancyRate);
    }

    private void fullAllBox(LockerManager lockerManager) {
        for (int i = 0; i < lockerManager.getCapacity(); i++) {
            lockerManager.savePackage();
        }
    }
}

package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Bag;
import com.wuhantoc.javasample.Locker.Ticket;

public interface Robot {

   Ticket savePackage(Bag bag);

   Bag getPackage(Ticket ticket);

}

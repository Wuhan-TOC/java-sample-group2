package com.wuhantoc.javasample.robot;

import com.wuhantoc.javasample.locker.Bag;
import com.wuhantoc.javasample.locker.Ticket;

public interface Robot {

   Ticket savePackage(Bag bag);

   Bag getPackage(Ticket ticket);

}

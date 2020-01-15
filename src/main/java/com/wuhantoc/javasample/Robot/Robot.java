package com.wuhantoc.javasample.Robot;

import com.wuhantoc.javasample.Locker.Box;
import com.wuhantoc.javasample.Locker.Ticket;

public interface Robot {

   Ticket savePackage();

   Box getPackage(Ticket ticket);

}

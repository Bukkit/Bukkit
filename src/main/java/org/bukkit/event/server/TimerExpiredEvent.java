package org.bukkit.event.server;

import org.bukkit.event.Event;
import org.bukkit.Timer;

/**
 * An event fired when an org.bukkit.Timer has expired
 *
 * @author thylordroot
 */
 public class TimerExpiredEvent extends TimerEvent {
	
	 /**
	  * Instantiate a new TimerExpiredEvent
	  */
	public TimerExpiredEvent(Timer timer, long fireTime) {
		super(Type.TIMER_EXPIRED, timer, fireTime);
	}
 };
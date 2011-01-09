package org.bukkit.time;

import org.bukkit.Server;

/**
 * An org.bukkit.Timer that operates with one-shot semantics.
 *
 * @author thylordroot
 *
 * This timer is set up to fire, at most, one time. When the target time is reached,
 * it shall signal a request to be removed from the scheduler.
 */
public class MonostableTimer extends AbstractTimer {
	
	/**
	 * Instantiate a new MonostableTimer from the supplied long timestamp
	 */
	public MonostableTimer(long target) {
		super(target);
	}
	
	/**
	 * Instantiate a new MonostableTimer from the supplied MinecraftTime
	 */
	public MonostableTimer(MinecraftTime ts) {
		super(ts);
	}
	
	/**
	 * Signal the scheduler that this timer is invalid
	 */
	public boolean update(long time) {
		return !hasExpired(time);
	}
	
	/**
	 * Named constructor that constructs a MonostableTimer relative 
	 * to the start of the closest day.
	 */
	public static MonostableTimer relToDay(long time, long offset) {
		return new MonostableTimer(MinecraftTime.relToDay(time, 
			offset));
	}
	
	
	/**
	 * Named constructor that constructs a MonostableTimer relative 
	 * to the start of the closest day.
	 */
	public static MonostableTimer relToDay(Server server, 
		long offset) {
		return new MonostableTimer(
			MinecraftTime.relToDay(server, offset));
	}
	
	/**
	 * Named constructor that constructs an MonostableTimer relative to the start
	 * of the closest day.
	 */
	public static MonostableTimer relToHour(long time, long offset) {
		return new MonostableTimer(MinecraftTime.relToHour(time, 
			offset));
	}
	
	public static MonostableTimer relToHour(Server server, 
		long offset) {
		return new MonostableTimer(MinecraftTime.relToHour(
			server, offset));
	}
};
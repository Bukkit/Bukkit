
package org.bukkit;

/**
 * An interface that specifies a schedule in which a org.bukkit.event.server.TimerEvent
 * should be fired.
 *
 * @author thylordroot
 *
 * Classes which implement this interface are candidates to be registered with
 * Bukkit's scheduling engine. 
 */
public interface Timer {
	
	/**
	 * Determine whether or not the Timer has expired
	 *
	 * @param time A timestamp in minecraft time.
	 *
	 * @return True if the timer has expired based on the supplied time. 
	 * Otherwise, false.
	 */
	public boolean hasExpired(long time);
	
	/**
	 * @param Get the time when the timer is next projected to expire.
	 */
	public long getNextTarget();
	
	/**
	 * @param Set the time when the target should next fire.
	 */
	public void setNextTarget(long time);
	
	/**
	 * Update the time.
	 *
	 * @warning This function should only be called internally; otherwise,
	 * Really Bad Stuff (TM) could happen.
	 *
	 * @return True if the timer is permitted to be kept. Otherwise, false.
	 *
	 */
	public boolean update(long curTime);
};
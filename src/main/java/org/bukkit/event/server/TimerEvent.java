package org.bukkit.event.server;

import org.bukkit.Server;
import org.bukkit.Timer;
import org.bukkit.event.Event;

/**
 * An event fired when an action involving a org.bukkit.Timer has occured.
 *
 * @author thylordroot
 */
public class TimerEvent extends ServerEvent {
	private long m_fireTime;
	private Timer m_timer;
	
	TimerEvent(Type type, Timer timer, long fireTime) {
		super(type);
		m_fireTime = fireTime;
		m_timer = timer;
	}
	
	/* Todo: convenience constructor which uses the current Minecraft time
	 * reported by the server.
	 */
	
	/**
	 * @brief Obtain the time that the TimerEvent was actually fired
	 */
	public long fireTime() {
		return m_fireTime;
	}
	
	/**
	 * @brief Obtain the Timer associated with this TimerEvent.
	 */
	public Timer timer() {
		return m_timer;
	}
}